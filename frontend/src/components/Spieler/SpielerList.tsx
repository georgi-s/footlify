// "use client";
// import React from "react";
// import { useState, useEffect } from "react";
// import { useToast } from "../ui/use-toast";
// import SpielerHeader from "./SpielerHeader";
// import SpielerSearch from "./SpielerSearch";
// import SpielerTable from "./SpielerTable";
// import SpielerCreateDialog from "../dialogs/SpielerCreateDialog";
// import SpielerEditDialog from "../dialogs/SpielerEditDialog";
// import SpielerDeleteDialog from "../dialogs/SpielerDeleteDialog";
// import { motion } from "framer-motion";
// import ThemeToggle from "../theme-toggle";
// import {
//   getSpieler,
//   createSpieler,
//   updateSpieler,
//   deleteSpieler as deleteSpielerApi,
// } from "../../services/spielerService";
// import type { SpielerDTO } from "../../types/SpielerDTO";

// // Typen für Spieler
// export type SpielerTyp = "Torwart" | "Abwehr" | "Mittelfeld" | "Sturm";

// export interface SpielerBase {
//   vorname: string;
//   nachname: string;
//   geburtsdatum: string;
//   typ: SpielerTyp;
//   spieleGespielt: number;
//   gelbeKarten: number;
//   roteKarten: number;
// }

// export interface Torwart extends SpielerBase {
//   typ: "Torwart";
//   gehalteneSchuesse: number;
//   zuNullSpiele: number;
// }

// export interface Abwehr extends SpielerBase {
//   typ: "Abwehr";
//   tacklingQuote: number;
//   abgefangenePassse: number;
// }

// export interface Mittelfeld extends SpielerBase {
//   typ: "Mittelfeld";
//   passquote: number;
//   vorlagen: number;
// }

// export interface Sturm extends SpielerBase {
//   typ: "Sturm";
//   tore: number;
//   schuesse: number;
// }

// export type Spieler = (Torwart | Abwehr | Mittelfeld | Sturm) & { id: number };

// // Hilfsfunktion zum Konvertieren von SpielerDTO zu Spieler
// const convertDTOToSpieler = (dto: SpielerDTO): Spieler => {
//   // Basis-Spieler-Eigenschaften
//   const baseSpieler = {
//     id: dto.playerId || 0,
//     vorname: dto.vorname,
//     nachname: dto.nachname,
//     geburtsdatum: dto.geburtsdatum,
//     spieleGespielt: dto.gespielteSpiele,
//     gelbeKarten: dto.gelbeKarten,
//     roteKarten: dto.roteKarten,
//   };

//   // Je nach Spielertyp die spezifischen Eigenschaften hinzufügen
//   switch (dto.spielerType) {
//     case "Torwart":
//       return {
//         ...baseSpieler,
//         typ: "Torwart" as const,
//         gehalteneSchuesse: dto.gehalteneSchuesse || 0,
//         zuNullSpiele: 0, // Dieses Feld gibt es nicht im DTO, daher Standardwert
//       };
//     case "Verteidiger":
//       return {
//         ...baseSpieler,
//         typ: "Abwehr" as const,
//         tacklingQuote: dto.gegraetschteZweikampfGewonnenProzent || 0,
//         abgefangenePassse: dto.abgefangenePassQuote || 0,
//       };
//     case "Mittelfeldspieler":
//       return {
//         ...baseSpieler,
//         typ: "Mittelfeld" as const,
//         passquote: dto.passquote || 0,
//         vorlagen: dto.anzahlVorlagen || 0,
//       };
//     case "Stürmer":
//       return {
//         ...baseSpieler,
//         typ: "Sturm" as const,
//         tore: dto.tore || 0,
//         schuesse: dto.schuesse || 0,
//       };
//     default:
//       // Fallback für unbekannte Typen
//       return {
//         ...baseSpieler,
//         typ: "Mittelfeld" as const,
//         passquote: 0,
//         vorlagen: 0,
//       };
//   }
// };

// // Hilfsfunktion zum Konvertieren von Spieler zu SpielerDTO
// const convertSpielerToDTO = (
//   spieler: Omit<Spieler, "id"> | Spieler
// ): Partial<SpielerDTO> => {
//   // Basis-DTO-Eigenschaften
//   const baseDTO: Partial<SpielerDTO> = {
//     vorname: spieler.vorname,
//     nachname: spieler.nachname,
//     geburtsdatum: spieler.geburtsdatum,
//     gespielteSpiele: spieler.spieleGespielt,
//     gelbeKarten: spieler.gelbeKarten,
//     roteKarten: spieler.roteKarten,
//     gesperrt: false, // Standardwert
//     vereinsbeitritt: new Date().toISOString(), // Standardwert
//   };

//   // ID hinzufügen, wenn vorhanden
//   if ("id" in spieler && spieler.id) {
//     baseDTO.playerId = spieler.id;
//   }

//   // Je nach Spielertyp die spezifischen Eigenschaften hinzufügen
//   switch (spieler.typ) {
//     case "Torwart":
//       return {
//         ...baseDTO,
//         spielerType: "Torwart",
//         gehalteneSchuesse: spieler.gehalteneSchuesse,
//         gegenTore: 0, // Standardwert
//         gehalteneElfmeter: 0, // Standardwert
//       };
//     case "Abwehr":
//       return {
//         ...baseDTO,
//         spielerType: "Verteidiger",
//         gegraetschteZweikampfGewonnenProzent: spieler.tacklingQuote,
//         kopfballDuellGewonnenProzent: 0, // Standardwert
//         abgefangenePassQuote: spieler.abgefangenePassse,
//       };
//     case "Mittelfeld":
//       return {
//         ...baseDTO,
//         spielerType: "Mittelfeldspieler",
//         anzahlVorlagen: spieler.vorlagen,
//         tore: 0, // Standardwert
//         passquote: spieler.passquote,
//       };
//     case "Sturm":
//       return {
//         ...baseDTO,
//         spielerType: "Stürmer",
//         tore: spieler.tore,
//         schuesse: spieler.schuesse,
//         torschusspraezision: 0, // Standardwert
//       };
//     default:
//       return baseDTO;
//   }
// };

// const SpielerList = () => {
//   const { toast } = useToast();
//   const [spieler, setSpieler] = useState<Spieler[]>([]);
//   const [filteredSpieler, setFilteredSpieler] = useState<Spieler[]>([]);
//   const [searchTerm, setSearchTerm] = useState("");
//   const [isLoading, setIsLoading] = useState(true);

//   // Dialog-States
//   const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);
//   const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);
//   const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
//   const [selectedSpieler, setSelectedSpieler] = useState<Spieler | null>(null);

//   // Lade Spielerdaten
//   useEffect(() => {
//     const loadSpieler = async () => {
//       setIsLoading(true);
//       try {
//         // Echten API-Aufruf verwenden
//         const spielerData = await getSpieler();
//         const convertedSpieler = spielerData.map(convertDTOToSpieler);
//         setSpieler(convertedSpieler);
//         setFilteredSpieler(convertedSpieler);
//       } catch (error) {
//         console.error("Fehler beim Laden der Spieler:", error);
//         toast({
//           title: "Fehler beim Laden",
//           description: "Die Spielerdaten konnten nicht geladen werden.",
//           variant: "destructive",
//         });
//       } finally {
//         setIsLoading(false);
//       }
//     };

//     loadSpieler();
//   }, [toast]);

//   // Suche nach Spielern
//   useEffect(() => {
//     if (searchTerm.trim() === "") {
//       setFilteredSpieler(spieler);
//     } else {
//       const lowercasedSearch = searchTerm.toLowerCase();
//       const filtered = spieler.filter(
//         (s) =>
//           s.vorname.toLowerCase().includes(lowercasedSearch) ||
//           s.nachname.toLowerCase().includes(lowercasedSearch)
//       );
//       setFilteredSpieler(filtered);
//     }
//   }, [searchTerm, spieler]);

//   // Handler für Spieler-Aktionen
//   const handleCreateSpieler = async (newSpieler: Omit<Spieler, "id">) => {
//     try {
//       setIsLoading(true);
//       // Konvertiere Spieler zu DTO
//       const spielerDTO = convertSpielerToDTO(newSpieler);

//       // API-Aufruf zum Erstellen des Spielers
//       const createdSpieler = await createSpieler(spielerDTO);

//       // Konvertiere zurück zu Spieler und aktualisiere State
//       const convertedSpieler = convertDTOToSpieler(createdSpieler);
//       setSpieler([...spieler, convertedSpieler]);

//       setIsCreateDialogOpen(false);
//       toast({
//         title: "Spieler erstellt",
//         description: `${newSpieler.vorname} ${newSpieler.nachname} wurde erfolgreich erstellt.`,
//       });
//     } catch (error) {
//       console.error("Fehler beim Erstellen des Spielers:", error);
//       toast({
//         title: "Fehler beim Erstellen",
//         description: "Der Spieler konnte nicht erstellt werden.",
//         variant: "destructive",
//       });
//     } finally {
//       setIsLoading(false);
//     }
//   };

//   const handleEditSpieler = async (updatedSpieler: Spieler) => {
//     try {
//       setIsLoading(true);
//       // Konvertiere Spieler zu DTO
//       const spielerDTO = convertSpielerToDTO(updatedSpieler);

//       // API-Aufruf zum Aktualisieren des Spielers
//       await updateSpieler(updatedSpieler.id, spielerDTO);

//       // Aktualisiere State
//       setSpieler(
//         spieler.map((s) => (s.id === updatedSpieler.id ? updatedSpieler : s))
//       );

//       setIsEditDialogOpen(false);
//       setSelectedSpieler(null);
//       toast({
//         title: "Spieler aktualisiert",
//         description: `${updatedSpieler.vorname} ${updatedSpieler.nachname} wurde erfolgreich aktualisiert.`,
//       });
//     } catch (error) {
//       console.error("Fehler beim Aktualisieren des Spielers:", error);
//       toast({
//         title: "Fehler beim Aktualisieren",
//         description: "Der Spieler konnte nicht aktualisiert werden.",
//         variant: "destructive",
//       });
//     } finally {
//       setIsLoading(false);
//     }
//   };

//   const handleDeleteSpieler = async (id: number) => {
//     try {
//       setIsLoading(true);
//       // API-Aufruf zum Löschen des Spielers
//       await deleteSpielerApi(id);

//       // Aktualisiere State
//       setSpieler(spieler.filter((s) => s.id !== id));

//       setIsDeleteDialogOpen(false);
//       setSelectedSpieler(null);
//       toast({
//         title: "Spieler gelöscht",
//         description: "Der Spieler wurde erfolgreich gelöscht.",
//       });
//     } catch (error) {
//       console.error("Fehler beim Löschen des Spielers:", error);
//       toast({
//         title: "Fehler beim Löschen",
//         description: "Der Spieler konnte nicht gelöscht werden.",
//         variant: "destructive",
//       });
//     } finally {
//       setIsLoading(false);
//     }
//   };

//   return (
//     <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-blue-50 to-sky-50 dark:from-gray-900 dark:via-indigo-950 dark:to-blue-950 p-4 md:p-8 overflow-hidden relative">
//       {/* Hintergrund-Elemente */}
//       <div className="absolute top-0 left-0 w-full h-full overflow-hidden z-0">
//         <div className="absolute top-10 left-10 w-64 h-64 bg-purple-400/20 dark:bg-purple-600/10 rounded-full blur-3xl"></div>
//         <div className="absolute bottom-10 right-10 w-80 h-80 bg-blue-400/20 dark:bg-blue-600/10 rounded-full blur-3xl"></div>
//         <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-96 h-96 bg-sky-400/10 dark:bg-sky-600/5 rounded-full blur-3xl"></div>
//       </div>

//       <div className="absolute top-4 right-4 z-50">
//         <ThemeToggle />
//       </div>

//       <motion.div
//         initial={{ opacity: 0, y: 20 }}
//         animate={{ opacity: 1, y: 0 }}
//         transition={{ duration: 0.5 }}
//         className="container mx-auto max-w-7xl relative z-10"
//       >
//         <div className="backdrop-blur-xl bg-white/70 dark:bg-gray-900/70 rounded-2xl shadow-2xl overflow-hidden border border-white/20 dark:border-gray-800/50">
//           {/* Header */}
//           <SpielerHeader onAddSpieler={() => setIsCreateDialogOpen(true)} />

//           {/* Suchleiste */}
//           <div className="p-6 border-b border-gray-200 dark:border-gray-800">
//             <SpielerSearch
//               searchTerm={searchTerm}
//               onSearchChange={setSearchTerm}
//             />
//           </div>

//           {/* Spielertabelle */}
//           <div className="p-6">
//             <SpielerTable
//               spieler={filteredSpieler}
//               isLoading={isLoading}
//               onEdit={(spieler) => {
//                 setSelectedSpieler(spieler);
//                 setIsEditDialogOpen(true);
//               }}
//               onDelete={(spieler) => {
//                 setSelectedSpieler(spieler);
//                 setIsDeleteDialogOpen(true);
//               }}
//             />
//           </div>
//         </div>
//       </motion.div>

//       {/* Dialoge */}
//       <SpielerCreateDialog
//         open={isCreateDialogOpen}
//         onOpenChange={setIsCreateDialogOpen}
//         onSubmit={handleCreateSpieler}
//       />

//       {selectedSpieler && (
//         <>
//           <SpielerEditDialog
//             open={isEditDialogOpen}
//             onOpenChange={setIsEditDialogOpen}
//             spieler={selectedSpieler}
//             onSubmit={handleEditSpieler}
//           />

//           <SpielerDeleteDialog
//             open={isDeleteDialogOpen}
//             onOpenChange={setIsDeleteDialogOpen}
//             spieler={selectedSpieler}
//             onConfirm={() => handleDeleteSpieler(selectedSpieler.id)}
//           />
//         </>
//       )}
//     </div>
//   );
// };

// export default SpielerList;
"use client";
import React, { useState, useEffect } from "react";
import { useToast } from "../ui/use-toast";
import SpielerHeader from "./SpielerHeader";
import SpielerSearch from "./SpielerSearch";
import SpielerTable from "./SpielerTable";
import SpielerCreateDialog from "../dialogs/SpielerCreateDialog";
import SpielerEditDialog from "../dialogs/SpielerEditDialog";
import SpielerDeleteDialog from "../dialogs/SpielerDeleteDialog";
import { motion } from "framer-motion";
import ThemeToggle from "../theme-toggle";
import {
  getSpieler,
  createSpieler,
  updateSpieler,
  deleteSpieler as deleteSpielerApi,
} from "../../services/spielerService";
import type { SpielerDTO } from "../../types/SpielerDTO";

// Typen für Spieler
export type SpielerTyp = "Torwart" | "Abwehr" | "Mittelfeld" | "Sturm";

export interface SpielerBase {
  vorname: string;
  nachname: string;
  geburtsdatum: string;
  typ: SpielerTyp;
  spieleGespielt: number;
  gelbeKarten: number;
  roteKarten: number;
}

export interface Torwart extends SpielerBase {
  typ: "Torwart";
  gehalteneSchuesse: number;
  zuNullSpiele: number;
}

export interface Abwehr extends SpielerBase {
  typ: "Abwehr";
  tacklingQuote: number;
  abgefangenePassse: number;
}

export interface Mittelfeld extends SpielerBase {
  typ: "Mittelfeld";
  passquote: number;
  vorlagen: number;
}

export interface Sturm extends SpielerBase {
  typ: "Sturm";
  tore: number;
  schuesse: number;
}

export type Spieler = (Torwart | Abwehr | Mittelfeld | Sturm) & { id: number };

// Type Guards für die verschiedenen Spielertypen
function isTorwart(spieler: SpielerBase): spieler is Torwart {
  return spieler.typ === "Torwart";
}

function isAbwehr(spieler: SpielerBase): spieler is Abwehr {
  return spieler.typ === "Abwehr";
}

function isMittelfeld(spieler: SpielerBase): spieler is Mittelfeld {
  return spieler.typ === "Mittelfeld";
}

function isSturm(spieler: SpielerBase): spieler is Sturm {
  return spieler.typ === "Sturm";
}

// Hilfsfunktion zum Konvertieren von SpielerDTO zu Spieler
const convertDTOToSpieler = (dto: SpielerDTO): Spieler => {
  // Basis-Spieler-Eigenschaften
  const baseSpieler = {
    id: dto.playerId || 0,
    vorname: dto.vorname,
    nachname: dto.nachname,
    geburtsdatum: dto.geburtsdatum,
    spieleGespielt: dto.gespielteSpiele,
    gelbeKarten: dto.gelbeKarten,
    roteKarten: dto.roteKarten,
  };

  // Je nach Spielertyp die spezifischen Eigenschaften hinzufügen
  switch (dto.spielerType) {
    case "Torwart":
      return {
        ...baseSpieler,
        typ: "Torwart" as const,
        gehalteneSchuesse: dto.gehalteneSchuesse || 0,
        zuNullSpiele: 0, // Dieses Feld gibt es nicht im DTO, daher Standardwert
      };
    case "Verteidiger":
      return {
        ...baseSpieler,
        typ: "Abwehr" as const,
        tacklingQuote: dto.gegraetschteZweikampfGewonnenProzent || 0,
        abgefangenePassse: dto.abgefangenePassQuote || 0,
      };
    case "Mittelfeldspieler":
      return {
        ...baseSpieler,
        typ: "Mittelfeld" as const,
        passquote: dto.passquote || 0,
        vorlagen: dto.anzahlVorlagen || 0,
      };
    case "Stürmer":
      return {
        ...baseSpieler,
        typ: "Sturm" as const,
        tore: dto.tore || 0,
        schuesse: dto.schuesse || 0,
      };
    default:
      // Fallback für unbekannte Typen
      return {
        ...baseSpieler,
        typ: "Mittelfeld" as const,
        passquote: 0,
        vorlagen: 0,
      };
  }
};

// Hilfsfunktion zum Konvertieren von Spieler zu SpielerDTO
const convertSpielerToDTO = (
  spieler: Omit<Spieler, "id"> | Spieler
): Partial<SpielerDTO> => {
  // Basis-DTO-Eigenschaften
  const baseDTO: Partial<SpielerDTO> = {
    vorname: spieler.vorname,
    nachname: spieler.nachname,
    geburtsdatum: spieler.geburtsdatum,
    gespielteSpiele: spieler.spieleGespielt,
    gelbeKarten: spieler.gelbeKarten,
    roteKarten: spieler.roteKarten,
    gesperrt: false, // Standardwert
    vereinsbeitritt: new Date().toISOString(), // Standardwert
  };

  // ID hinzufügen, wenn vorhanden
  if ("id" in spieler && spieler.id) {
    baseDTO.playerId = spieler.id;
  }

  // Je nach Spielertyp die spezifischen Eigenschaften hinzufügen
  if (isTorwart(spieler)) {
    return {
      ...baseDTO,
      spielerType: "Torwart",
      gehalteneSchuesse: spieler.gehalteneSchuesse,
      gegenTore: 0, // Standardwert
      gehalteneElfmeter: 0, // Standardwert
    };
  } else if (isAbwehr(spieler)) {
    return {
      ...baseDTO,
      spielerType: "Verteidiger",
      gegraetschteZweikampfGewonnenProzent: spieler.tacklingQuote,
      kopfballDuellGewonnenProzent: 0, // Standardwert
      abgefangenePassQuote: spieler.abgefangenePassse,
    };
  } else if (isMittelfeld(spieler)) {
    return {
      ...baseDTO,
      spielerType: "Mittelfeldspieler",
      anzahlVorlagen: spieler.vorlagen,
      tore: 0, // Standardwert
      passquote: spieler.passquote,
    };
  } else if (isSturm(spieler)) {
    return {
      ...baseDTO,
      spielerType: "Stürmer",
      tore: spieler.tore,
      schuesse: spieler.schuesse,
      torschusspraezision: 0, // Standardwert
    };
  } else {
    return baseDTO;
  }
};

const SpielerList = () => {
  const { toast } = useToast();
  const [spieler, setSpieler] = useState<Spieler[]>([]);
  const [filteredSpieler, setFilteredSpieler] = useState<Spieler[]>([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [isLoading, setIsLoading] = useState(true);

  // Dialog-States
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);
  const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);
  const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
  const [selectedSpieler, setSelectedSpieler] = useState<Spieler | null>(null);

  // Lade Spielerdaten
  useEffect(() => {
    const loadSpieler = async () => {
      setIsLoading(true);
      try {
        // Echten API-Aufruf verwenden
        const spielerData = await getSpieler();
        const convertedSpieler = spielerData.map(convertDTOToSpieler);
        setSpieler(convertedSpieler);
        setFilteredSpieler(convertedSpieler);
      } catch (error) {
        console.error("Fehler beim Laden der Spieler:", error);
        toast({
          title: "Fehler beim Laden",
          description: "Die Spielerdaten konnten nicht geladen werden.",
          variant: "destructive",
        });
      } finally {
        setIsLoading(false);
      }
    };

    loadSpieler();
  }, [toast]);

  // Suche nach Spielern
  useEffect(() => {
    if (searchTerm.trim() === "") {
      setFilteredSpieler(spieler);
    } else {
      const lowercasedSearch = searchTerm.toLowerCase();
      const filtered = spieler.filter(
        (s) =>
          s.vorname.toLowerCase().includes(lowercasedSearch) ||
          s.nachname.toLowerCase().includes(lowercasedSearch)
      );
      setFilteredSpieler(filtered);
    }
  }, [searchTerm, spieler]);

  // Handler für Spieler-Aktionen
  const handleCreateSpieler = async (newSpieler: Omit<Spieler, "id">) => {
    try {
      setIsLoading(true);
      // Konvertiere Spieler zu DTO
      const spielerDTO = convertSpielerToDTO(newSpieler);

      // API-Aufruf zum Erstellen des Spielers
      const createdSpieler = await createSpieler(spielerDTO);

      // Konvertiere zurück zu Spieler und aktualisiere State
      const convertedSpieler = convertDTOToSpieler(createdSpieler);
      setSpieler([...spieler, convertedSpieler]);

      setIsCreateDialogOpen(false);
      toast({
        title: "Spieler erstellt",
        description: `${newSpieler.vorname} ${newSpieler.nachname} wurde erfolgreich erstellt.`,
      });
    } catch (error) {
      console.error("Fehler beim Erstellen des Spielers:", error);
      toast({
        title: "Fehler beim Erstellen",
        description: "Der Spieler konnte nicht erstellt werden.",
        variant: "destructive",
      });
    } finally {
      setIsLoading(false);
    }
  };

  const handleEditSpieler = async (updatedSpieler: Spieler) => {
    try {
      setIsLoading(true);
      // Konvertiere Spieler zu DTO
      const spielerDTO = convertSpielerToDTO(updatedSpieler);

      // API-Aufruf zum Aktualisieren des Spielers
      await updateSpieler(updatedSpieler.id, spielerDTO);

      // Aktualisiere State
      setSpieler(
        spieler.map((s) => (s.id === updatedSpieler.id ? updatedSpieler : s))
      );

      setIsEditDialogOpen(false);
      setSelectedSpieler(null);
      toast({
        title: "Spieler aktualisiert",
        description: `${updatedSpieler.vorname} ${updatedSpieler.nachname} wurde erfolgreich aktualisiert.`,
      });
    } catch (error) {
      console.error("Fehler beim Aktualisieren des Spielers:", error);
      toast({
        title: "Fehler beim Aktualisieren",
        description: "Der Spieler konnte nicht aktualisiert werden.",
        variant: "destructive",
      });
    } finally {
      setIsLoading(false);
    }
  };

  const handleDeleteSpieler = async (id: number) => {
    try {
      setIsLoading(true);
      // API-Aufruf zum Löschen des Spielers
      await deleteSpielerApi(id);

      // Aktualisiere State
      setSpieler(spieler.filter((s) => s.id !== id));

      setIsDeleteDialogOpen(false);
      setSelectedSpieler(null);
      toast({
        title: "Spieler gelöscht",
        description: "Der Spieler wurde erfolgreich gelöscht.",
      });
    } catch (error) {
      console.error("Fehler beim Löschen des Spielers:", error);
      toast({
        title: "Fehler beim Löschen",
        description: "Der Spieler konnte nicht gelöscht werden.",
        variant: "destructive",
      });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-blue-50 to-sky-50 dark:from-gray-900 dark:via-indigo-950 dark:to-blue-950 p-4 md:p-8 overflow-hidden relative">
      {/* Hintergrund-Elemente */}
      <div className="absolute top-0 left-0 w-full h-full overflow-hidden z-0">
        <div className="absolute top-10 left-10 w-64 h-64 bg-purple-400/20 dark:bg-purple-600/10 rounded-full blur-3xl"></div>
        <div className="absolute bottom-10 right-10 w-80 h-80 bg-blue-400/20 dark:bg-blue-600/10 rounded-full blur-3xl"></div>
        <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-96 h-96 bg-sky-400/10 dark:bg-sky-600/5 rounded-full blur-3xl"></div>
      </div>

      <div className="absolute top-4 right-4 z-50">
        <ThemeToggle />
      </div>

      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="container mx-auto max-w-7xl relative z-10"
      >
        <div className="backdrop-blur-xl bg-white/70 dark:bg-gray-900/70 rounded-2xl shadow-2xl overflow-hidden border border-white/20 dark:border-gray-800/50">
          {/* Header */}
          <SpielerHeader onAddSpieler={() => setIsCreateDialogOpen(true)} />

          {/* Suchleiste */}
          <div className="p-6 border-b border-gray-200 dark:border-gray-800">
            <SpielerSearch
              searchTerm={searchTerm}
              onSearchChange={setSearchTerm}
            />
          </div>

          {/* Spielertabelle */}
          <div className="p-6">
            <SpielerTable
              spieler={filteredSpieler}
              isLoading={isLoading}
              onEdit={(spieler) => {
                setSelectedSpieler(spieler);
                setIsEditDialogOpen(true);
              }}
              onDelete={(spieler) => {
                setSelectedSpieler(spieler);
                setIsDeleteDialogOpen(true);
              }}
            />
          </div>
        </div>
      </motion.div>

      {/* Dialoge */}
      <SpielerCreateDialog
        open={isCreateDialogOpen}
        onOpenChange={setIsCreateDialogOpen}
        onSubmit={handleCreateSpieler}
      />

      {selectedSpieler && (
        <>
          <SpielerEditDialog
            open={isEditDialogOpen}
            onOpenChange={setIsEditDialogOpen}
            spieler={selectedSpieler}
            onSubmit={handleEditSpieler}
          />

          <SpielerDeleteDialog
            open={isDeleteDialogOpen}
            onOpenChange={setIsDeleteDialogOpen}
            spieler={selectedSpieler}
            onConfirm={() => handleDeleteSpieler(selectedSpieler.id)}
          />
        </>
      )}
    </div>
  );
};

export default SpielerList;
