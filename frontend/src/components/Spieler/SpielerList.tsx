"use client";
import React from "react";
import { useState, useEffect } from "react";
import { useToast } from "../ui/use-toast";
import SpielerHeader from "./SpielerHeader";
import SpielerSearch from "./SpielerSearch";
import SpielerTable from "./SpielerTable";
import SpielerCreateDialog from "../dialogs/SpielerCreateDialog";
import SpielerEditDialog from "../dialogs/SpielerEditDialog";
import SpielerDeleteDialog from "../dialogs/SpielerDeleteDialog";
import { motion } from "framer-motion";
import ThemeToggle from "../theme-toggle";

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

// Mock-Daten für Spieler
const mockSpieler: Spieler[] = [
  {
    id: 1,
    vorname: "Manuel",
    nachname: "Neuer",
    geburtsdatum: "1986-03-27",
    typ: "Torwart",
    spieleGespielt: 450,
    gelbeKarten: 12,
    roteKarten: 1,
    gehalteneSchuesse: 1200,
    zuNullSpiele: 200,
  },
  {
    id: 2,
    vorname: "Joshua",
    nachname: "Kimmich",
    geburtsdatum: "1995-02-08",
    typ: "Mittelfeld",
    spieleGespielt: 320,
    gelbeKarten: 45,
    roteKarten: 2,
    passquote: 89.5,
    vorlagen: 78,
  },
  {
    id: 3,
    vorname: "Mats",
    nachname: "Hummels",
    geburtsdatum: "1988-12-16",
    typ: "Abwehr",
    spieleGespielt: 380,
    gelbeKarten: 52,
    roteKarten: 3,
    tacklingQuote: 76.2,
    abgefangenePassse: 540,
  },
  {
    id: 4,
    vorname: "Robert",
    nachname: "Lewandowski",
    geburtsdatum: "1988-08-21",
    typ: "Sturm",
    spieleGespielt: 410,
    gelbeKarten: 30,
    roteKarten: 0,
    tore: 312,
    schuesse: 720,
  },
  {
    id: 5,
    vorname: "Thomas",
    nachname: "Müller",
    geburtsdatum: "1989-09-13",
    typ: "Mittelfeld",
    spieleGespielt: 430,
    gelbeKarten: 40,
    roteKarten: 1,
    passquote: 82.7,
    vorlagen: 150,
  },
];

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
        // Hier würde normalerweise ein API-Aufruf stehen
        // Simuliere API-Aufruf mit setTimeout
        setTimeout(() => {
          setSpieler(mockSpieler);
          setFilteredSpieler(mockSpieler);
          setIsLoading(false);
        }, 800);
      } catch (error) {
        console.error("Fehler beim Laden der Spieler:", error);
        toast({
          title: "Fehler beim Laden",
          description: "Die Spielerdaten konnten nicht geladen werden.",
          variant: "destructive",
        });
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
  const handleCreateSpieler = (newSpieler: Omit<Spieler, "id">) => {
    // Normalerweise würde hier ein API-Aufruf stehen
    const spielerWithId = {
      ...newSpieler,
      id: Math.max(0, ...spieler.map((s) => s.id)) + 1,
    } as Spieler;

    setSpieler([...spieler, spielerWithId]);
    setIsCreateDialogOpen(false);
    toast({
      title: "Spieler erstellt",
      description: `${newSpieler.vorname} ${newSpieler.nachname} wurde erfolgreich erstellt.`,
    });
  };

  const handleEditSpieler = (updatedSpieler: Spieler) => {
    // Normalerweise würde hier ein API-Aufruf stehen
    setSpieler(
      spieler.map((s) => (s.id === updatedSpieler.id ? updatedSpieler : s))
    );
    setIsEditDialogOpen(false);
    setSelectedSpieler(null);
    toast({
      title: "Spieler aktualisiert",
      description: `${updatedSpieler.vorname} ${updatedSpieler.nachname} wurde erfolgreich aktualisiert.`,
    });
  };

  const handleDeleteSpieler = (id: number) => {
    // Normalerweise würde hier ein API-Aufruf stehen
    setSpieler(spieler.filter((s) => s.id !== id));
    setIsDeleteDialogOpen(false);
    setSelectedSpieler(null);
    toast({
      title: "Spieler gelöscht",
      description: "Der Spieler wurde erfolgreich gelöscht.",
    });
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
