"use client";
import React from "react";
import { useState, useEffect } from "react";
import { motion } from "framer-motion";
import { useToast } from "../ui/use-toast";
import SpielplanHeader from "../components/SpielplanHeader";
import SpielplanFilter from "../components/SpielplanFilter";
import SpielplanTable from "../components/SpielplanTable";
import SpielplanCreateDialog from "../dialogs/SpielplanCreateDialog";
import SpielplanEditDialog from "../dialogs/SpielplanEditDialog";
import SpielplanDeleteDialog from "../dialogs/SpeilplanDeleteDialog";
import ThemeToggle from "../theme-toggle";

// Typen für Spiele
export interface Spiel {
  id: number;
  heimMannschaft: string;
  heimMannschaftId: number;
  gastMannschaft: string;
  gastMannschaftId: number;
  datum: string;
  uhrzeit: string;
  ort: string;
  liga: string;
  status: "Geplant" | "Live" | "Beendet" | "Verschoben" | "Abgesagt";
  heimTore?: number;
  gastTore?: number;
}

// Mock-Daten für Spiele
const mockSpiele: Spiel[] = [
  {
    id: 1,
    heimMannschaft: "FC Bayern München",
    heimMannschaftId: 1,
    gastMannschaft: "Borussia Dortmund",
    gastMannschaftId: 2,
    datum: "2025-04-15",
    uhrzeit: "15:30",
    ort: "Allianz Arena",
    liga: "Bundesliga",
    status: "Geplant",
  },
  {
    id: 2,
    heimMannschaft: "Borussia Dortmund",
    heimMannschaftId: 2,
    gastMannschaft: "FC Bayern München",
    gastMannschaftId: 1,
    datum: "2025-03-10",
    uhrzeit: "18:30",
    ort: "Signal Iduna Park",
    liga: "Bundesliga",
    status: "Beendet",
    heimTore: 2,
    gastTore: 2,
  },
  {
    id: 3,
    heimMannschaft: "FC Bayern München",
    heimMannschaftId: 1,
    gastMannschaft: "RB Leipzig",
    gastMannschaftId: 3,
    datum: "2025-05-20",
    uhrzeit: "20:30",
    ort: "Allianz Arena",
    liga: "Bundesliga",
    status: "Geplant",
  },
  {
    id: 4,
    heimMannschaft: "Bayer Leverkusen",
    heimMannschaftId: 4,
    gastMannschaft: "FC Bayern München",
    gastMannschaftId: 1,
    datum: "2025-02-28",
    uhrzeit: "15:30",
    ort: "BayArena",
    liga: "Bundesliga",
    status: "Beendet",
    heimTore: 1,
    gastTore: 3,
  },
  {
    id: 5,
    heimMannschaft: "Borussia Dortmund",
    heimMannschaftId: 2,
    gastMannschaft: "RB Leipzig",
    gastMannschaftId: 3,
    datum: "2025-04-05",
    uhrzeit: "18:30",
    ort: "Signal Iduna Park",
    liga: "Bundesliga",
    status: "Geplant",
  },
];

// Mock-Daten für Mannschaften (für Filter und Dialoge)
export const mockMannschaften = [
  { id: 1, name: "FC Bayern München" },
  { id: 2, name: "Borussia Dortmund" },
  { id: 3, name: "RB Leipzig" },
  { id: 4, name: "Bayer Leverkusen" },
  { id: 5, name: "Eintracht Frankfurt" },
  { id: 6, name: "VfL Wolfsburg" },
];

// Mock-Daten für Ligen (für Filter und Dialoge)
export const mockLigen = [
  "Bundesliga",
  "2. Bundesliga",
  "3. Liga",
  "DFB-Pokal",
  "Champions League",
];

const Spielplan = () => {
  const { toast } = useToast();
  const [spiele, setSpiele] = useState<Spiel[]>([]);
  const [filteredSpiele, setFilteredSpiele] = useState<Spiel[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  // Filter-States
  const [mannschaftFilter, setMannschaftFilter] = useState<number | null>(null);
  const [ligaFilter, setLigaFilter] = useState<string | null>(null);
  const [statusFilter, setStatusFilter] = useState<string | null>(null);
  const [datumVonFilter, setDatumVonFilter] = useState<string>("");
  const [datumBisFilter, setDatumBisFilter] = useState<string>("");

  // Dialog-States
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);
  const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);
  const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
  const [selectedSpiel, setSelectedSpiel] = useState<Spiel | null>(null);

  // Lade Spieldaten
  useEffect(() => {
    const loadSpiele = async () => {
      setIsLoading(true);
      try {
        // Hier würde normalerweise ein API-Aufruf stehen
        // Simuliere API-Aufruf mit setTimeout
        setTimeout(() => {
          setSpiele(mockSpiele);
          setFilteredSpiele(mockSpiele);
          setIsLoading(false);
        }, 800);
      } catch (error) {
        console.error("Fehler beim Laden der Spiele:", error);
        toast({
          title: "Fehler beim Laden",
          description: "Die Spieldaten konnten nicht geladen werden.",
          variant: "destructive",
        });
        setIsLoading(false);
      }
    };

    loadSpiele();
  }, [toast]);

  // Filtere Spiele basierend auf den Filterkriterien
  useEffect(() => {
    let result = [...spiele];

    // Filter nach Mannschaft
    if (mannschaftFilter !== null) {
      result = result.filter(
        (spiel) =>
          spiel.heimMannschaftId === mannschaftFilter ||
          spiel.gastMannschaftId === mannschaftFilter
      );
    }

    // Filter nach Liga
    if (ligaFilter) {
      result = result.filter((spiel) => spiel.liga === ligaFilter);
    }

    // Filter nach Status
    if (statusFilter) {
      result = result.filter((spiel) => spiel.status === statusFilter);
    }

    // Filter nach Datum (von)
    if (datumVonFilter) {
      result = result.filter((spiel) => spiel.datum >= datumVonFilter);
    }

    // Filter nach Datum (bis)
    if (datumBisFilter) {
      result = result.filter((spiel) => spiel.datum <= datumBisFilter);
    }

    setFilteredSpiele(result);
  }, [
    spiele,
    mannschaftFilter,
    ligaFilter,
    statusFilter,
    datumVonFilter,
    datumBisFilter,
  ]);

  // Handler für Spiel-Aktionen
  const handleCreateSpiel = (newSpiel: Omit<Spiel, "id">) => {
    // Normalerweise würde hier ein API-Aufruf stehen
    const spielWithId = {
      ...newSpiel,
      id: Math.max(0, ...spiele.map((s) => s.id)) + 1,
    };

    setSpiele([...spiele, spielWithId]);
    setIsCreateDialogOpen(false);
    toast({
      title: "Spiel erstellt",
      description: `${newSpiel.heimMannschaft} vs. ${newSpiel.gastMannschaft} wurde erfolgreich erstellt.`,
    });
  };

  const handleEditSpiel = (updatedSpiel: Spiel) => {
    // Normalerweise würde hier ein API-Aufruf stehen
    setSpiele(spiele.map((s) => (s.id === updatedSpiel.id ? updatedSpiel : s)));
    setIsEditDialogOpen(false);
    setSelectedSpiel(null);
    toast({
      title: "Spiel aktualisiert",
      description: `${updatedSpiel.heimMannschaft} vs. ${updatedSpiel.gastMannschaft} wurde erfolgreich aktualisiert.`,
    });
  };

  const handleDeleteSpiel = (id: number) => {
    // Normalerweise würde hier ein API-Aufruf stehen
    setSpiele(spiele.filter((s) => s.id !== id));
    setIsDeleteDialogOpen(false);
    setSelectedSpiel(null);
    toast({
      title: "Spiel gelöscht",
      description: "Das Spiel wurde erfolgreich gelöscht.",
    });
  };

  // Filter zurücksetzen
  const resetFilter = () => {
    setMannschaftFilter(null);
    setLigaFilter(null);
    setStatusFilter(null);
    setDatumVonFilter("");
    setDatumBisFilter("");
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
          <SpielplanHeader onAddSpiel={() => setIsCreateDialogOpen(true)} />

          {/* Filter */}
          <div className="p-6 border-b border-gray-200 dark:border-gray-800">
            <SpielplanFilter
              mannschaften={mockMannschaften}
              ligen={mockLigen}
              mannschaftFilter={mannschaftFilter}
              ligaFilter={ligaFilter}
              statusFilter={statusFilter}
              datumVonFilter={datumVonFilter}
              datumBisFilter={datumBisFilter}
              onMannschaftFilterChange={setMannschaftFilter}
              onLigaFilterChange={setLigaFilter}
              onStatusFilterChange={setStatusFilter}
              onDatumVonFilterChange={setDatumVonFilter}
              onDatumBisFilterChange={setDatumBisFilter}
              onResetFilter={resetFilter}
            />
          </div>

          {/* Spielplan-Tabelle */}
          <div className="p-6">
            <SpielplanTable
              spiele={filteredSpiele}
              isLoading={isLoading}
              onEdit={(spiel) => {
                setSelectedSpiel(spiel);
                setIsEditDialogOpen(true);
              }}
              onDelete={(spiel) => {
                setSelectedSpiel(spiel);
                setIsDeleteDialogOpen(true);
              }}
            />
          </div>
        </div>
      </motion.div>

      {/* Dialoge */}
      <SpielplanCreateDialog
        open={isCreateDialogOpen}
        onOpenChange={setIsCreateDialogOpen}
        onSubmit={handleCreateSpiel}
        mannschaften={mockMannschaften}
        ligen={mockLigen}
      />

      {selectedSpiel && (
        <>
          <SpielplanEditDialog
            open={isEditDialogOpen}
            onOpenChange={setIsEditDialogOpen}
            spiel={selectedSpiel}
            onSubmit={handleEditSpiel}
            mannschaften={mockMannschaften}
            ligen={mockLigen}
          />

          <SpielplanDeleteDialog
            open={isDeleteDialogOpen}
            onOpenChange={setIsDeleteDialogOpen}
            spiel={selectedSpiel}
            onConfirm={() => handleDeleteSpiel(selectedSpiel.id)}
          />
        </>
      )}
    </div>
  );
};

export default Spielplan;
