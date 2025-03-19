"use client";

import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import { useToast } from "../ui/use-toast";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "../ui/tabs";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select";
import { Input } from "../ui/input";
import { Button } from "../ui/button";
import { Card, CardContent } from "../ui/card";
import { Separator } from "../ui/separator";
import { Badge } from "../ui/badge";
import {
  Trophy,
  Shield,
  Star,
  Users,
  Zap,
  Briefcase,
  Calendar,
  Award,
  Save,
  CheckCircle,
  Loader2,
  UserPlus,
} from "lucide-react";
import {
  mannschaftApi,
  spielerApi,
  ApiResponseData,
} from "../../services/api/index";
import type {
  Spieler as ApiSpieler,
  Mannschaft as ApiMannschaft,
  FormationEntity,
  LigaEntity,
} from "../../types";
import SpielerTable from "../Spieler/SpielerTable";

// Frontend-spezifische Typen als Type Aliases
export type FrontendSpieler = {
  id: number;
  vorname: string;
  nachname: string;
  position: string;
  bewertung: number;
  alter: number;
  gehalt: number;
  trikotnummer: number;
  mannschaftId?: number;
};

export type FrontendMannschaft = {
  id: number;
  name: string;
  trainer: string;
  formation: FormationEntity | null;
  liga: LigaEntity | null;
  spieler: FrontendSpieler[];

  // Für String-Werte in der UI
  formationString?: string;
  ligaString?: string;
};

// Konvertierungsfunktionen
const convertApiSpielerToSpieler = (
  apiSpieler: ApiSpieler
): FrontendSpieler => ({
  id: apiSpieler.id,
  vorname: apiSpieler.name.split(" ")[0],
  nachname: apiSpieler.name.split(" ")[1] || "",
  position: apiSpieler.position,
  bewertung: apiSpieler.bewertung || 75, // Default Bewertung falls nicht vorhanden
  alter: apiSpieler.alter,
  gehalt: apiSpieler.gehalt,
  trikotnummer: apiSpieler.trikotnummer,
  mannschaftId: apiSpieler.mannschaftId,
});

const convertApiMannschaftToMannschaft = (
  apiMannschaft: ApiMannschaft
): FrontendMannschaft => {
  const formationString = apiMannschaft.formation?.bezeichnung || "4-4-2";
  const ligaString = apiMannschaft.liga?.name || "BUNDESLIGA";

  return {
    id: apiMannschaft.id,
    name: apiMannschaft.name,
    trainer: apiMannschaft.trainer || "",
    formation: apiMannschaft.formation,
    liga: apiMannschaft.liga,
    formationString: formationString,
    ligaString: ligaString,
    spieler: (apiMannschaft.spieler || []).map(convertApiSpielerToSpieler),
  };
};

const MannschaftManagementPanel: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { toast } = useToast();
  const [selectedClub, setSelectedClub] = useState<FrontendMannschaft | null>(
    null
  );
  const [clubs, setClubs] = useState<FrontendMannschaft[]>([]);
  const [availablePlayers, setAvailablePlayers] = useState<FrontendSpieler[]>(
    []
  );
  const [teamPlayers, setTeamPlayers] = useState<FrontendSpieler[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [sortConfig, setSortConfig] = useState({ key: "", direction: "" });

  // Type Guard Funktionen
  const isMannschaft = (obj: any): obj is FrontendMannschaft => {
    return (
      obj &&
      typeof obj.id === "number" &&
      typeof obj.name === "string" &&
      typeof obj.trainer === "string" &&
      typeof obj.formation === "string" &&
      typeof obj.liga === "string" &&
      Array.isArray(obj.spieler)
    );
  };

  const isSpieler = (obj: any): obj is FrontendSpieler => {
    return (
      obj &&
      typeof obj.id === "number" &&
      typeof obj.vorname === "string" &&
      typeof obj.nachname === "string" &&
      typeof obj.position === "string" &&
      typeof obj.bewertung === "number"
    );
  };

  useEffect(() => {
    if (!id) return;
    
    // Lade Daten der spezifischen Mannschaft
    const fetchMannschaftData = async () => {
      setIsLoading(true);
      try {
        // Hole die spezifische Mannschaft mit der ID aus der URL
        const mannschaftResponse = await mannschaftApi.getById(Number(id));
        const spielerResponse = await spielerApi.getAll();
        
        // Konvertiere die Mannschaft
        const convertedMannschaft = convertApiMannschaftToMannschaft(mannschaftResponse.data);
        
        // Konvertiere alle Spieler
        const convertedPlayers = spielerResponse.data.map(convertApiSpielerToSpieler);
        
        // Setze die selektierte Mannschaft
        setSelectedClub(convertedMannschaft);
        
        // Setze die Spieler der Mannschaft
        setTeamPlayers(convertedPlayers.filter(player => player.mannschaftId === Number(id)));
        
        // Setze die verfügbaren Spieler (die nicht zu einer Mannschaft gehören)
        setAvailablePlayers(convertedPlayers.filter(player => !player.mannschaftId));
        
        // Hole alle Mannschaften für die Dropdown-Liste
        const clubsResponse = await mannschaftApi.getAll();
        setClubs(clubsResponse.data.map(convertApiMannschaftToMannschaft));
      } catch (error) {
        console.error("Fehler beim Laden der Mannschaftsdaten:", error);
        toast({
          title: "Fehler beim Laden der Mannschaftsdaten",
          description: `Die Mannschaft mit der ID ${id} konnte nicht geladen werden.`,
          variant: "destructive",
        });
        navigate('/mannschaften'); // Zurück zur Übersicht navigieren
      } finally {
        setIsLoading(false);
      }
    };

    fetchMannschaftData();
  }, [id, toast, navigate]);

  const refreshData = async () => {
    setIsLoading(true);
    try {
      const responses = await Promise.all([
        mannschaftApi.getAll(),
        spielerApi.getAll(),
      ]);

      const [clubsResponse, playersResponse] = responses as [
        ApiResponseData<ApiMannschaft[]>,
        ApiResponseData<ApiSpieler[]>
      ];

      const convertedClubs = clubsResponse.data.map((club) => {
        const mannschaft = convertApiMannschaftToMannschaft(club);
        if (!isMannschaft(mannschaft)) {
          throw new Error(`Ungültige Mannschaftsdaten für ID ${club.id}`);
        }
        return mannschaft;
      });
      setClubs(convertedClubs);

      const convertedPlayers = playersResponse.data.map((player) => {
        const spieler = convertApiSpielerToSpieler(player);
        if (!isSpieler(spieler)) {
          throw new Error(`Ungültige Spielerdaten für ID ${player.id}`);
        }
        return spieler;
      });

      // Nur verfügbare Spieler anzeigen (ohne Mannschaft)
      setAvailablePlayers(
        convertedPlayers.filter((player) => !player.mannschaftId)
      );
    } catch (error) {
      toast({
        title: "Fehler beim Laden der Daten",
        description: "Die Daten konnten nicht aktualisiert werden.",
        variant: "destructive",
      });
    } finally {
      setIsLoading(false);
    }
  };

  const handleClubChange = async (clubId: string) => {
    setIsLoading(true);
    try {
      const response = await mannschaftApi.getById(Number.parseInt(clubId));
      const apiResponse = response as ApiResponseData<ApiMannschaft>;
      const mannschaft = convertApiMannschaftToMannschaft(apiResponse.data);

      if (!isMannschaft(mannschaft)) {
        throw new Error(
          `Ungültige Mannschaftsdaten für ID ${apiResponse.data.id}`
        );
      }

      const validSpieler = mannschaft.spieler.map((spieler) => {
        if (!isSpieler(spieler)) {
          throw new Error(
            `Ungültige Spielerdaten in Mannschaft ${mannschaft.id}`
          );
        }
        return spieler;
      });

      setSelectedClub(mannschaft);
      setTeamPlayers(validSpieler);
    } catch (error) {
      toast({
        title: "Fehler beim Laden der Mannschaftsdetails",
        description: "Die Mannschaftsdetails konnten nicht geladen werden.",
        variant: "destructive",
      });
    } finally {
      setIsLoading(false);
    }
  };

  const handlePlayerTransfer = async (playerId: number, toTeam: boolean) => {
    try {
      await spielerApi.transfer(
        playerId,
        toTeam ? selectedClub?.id : undefined
      );

      // Aktualisiere die Spielerlisten
      await refreshData();
      if (selectedClub) {
        await handleClubChange(selectedClub.id.toString());
      }

      toast({
        title: "Transfer erfolgreich",
        description: `Spieler wurde ${
          toTeam ? "zur Mannschaft hinzugefügt" : "aus der Mannschaft entfernt"
        }.`,
      });
    } catch (error) {
      toast({
        title: "Fehler beim Transfer",
        description: "Der Spielertransfer konnte nicht durchgeführt werden.",
        variant: "destructive",
      });
    }
  };

  const handleFormationCheck = async () => {
    if (!selectedClub) return;

    try {
      const response = await mannschaftApi.checkFormation(selectedClub.id);
      toast({
        title: response.data.valid ? "Formation gültig" : "Formation ungültig",
        description: response.data.message,
        variant: response.data.valid ? "default" : "destructive",
      });
    } catch (error) {
      toast({
        title: "Fehler bei der Formationsprüfung",
        description: "Die Formation konnte nicht überprüft werden.",
        variant: "destructive",
      });
    }
  };

  const handleUpdateTeam = async () => {
    if (!selectedClub) return;

    try {
      await mannschaftApi.update(selectedClub.id, {
        trainer: selectedClub.trainer,
        formation: selectedClub.formationString, // Nur String senden
        liga: selectedClub.ligaString, // Nur String senden
      });

      toast({
        title: "Mannschaft aktualisiert",
        description: "Die Mannschaftsdaten wurden erfolgreich aktualisiert.",
      });
    } catch (error) {
      toast({
        title: "Fehler beim Aktualisieren",
        description: "Die Mannschaftsdaten konnten nicht aktualisiert werden.",
        variant: "destructive",
      });
    }
  };

  // Calculate team rating
  const calculateTeamRating = (team: FrontendMannschaft) => {
    if (!team.spieler.length) return 0;
    const totalRating = team.spieler.reduce(
      (sum, player) => sum + player.bewertung,
      0
    );
    return Math.round(totalRating / team.spieler.length);
  };

  // Get rating color based on value
  const getRatingColor = (rating: number) => {
    if (rating >= 85) return "from-green-400 to-emerald-600";
    if (rating >= 75) return "from-yellow-400 to-amber-600";
    return "from-red-400 to-rose-600";
  };

  // Get formation preview
  const getFormationPreview = (formation: string) => {
    const formationMap: { [key: string]: string[][] } = {
      "4-4-2": [
        ["", "", "□", "□", "", ""],
        ["", "□", "□", "□", "□", ""],
        ["□", "□", "", "", "□", "□"],
        ["", "", "□", "", "", ""],
      ],
      "4-3-3": [
        ["", "□", "", "□", "", ""],
        ["□", "", "□", "", "□", ""],
        ["", "□", "□", "□", "", ""],
        ["", "", "□", "", "", ""],
      ],
      "3-5-2": [
        ["", "", "□", "□", "", ""],
        ["□", "□", "□", "□", "□", ""],
        ["", "□", "", "□", "", ""],
        ["", "", "□", "", "", ""],
      ],
    };

    const formationLayout = formationMap[formation] || formationMap["4-4-2"];

    return (
      <div className="grid grid-cols-6 gap-1">
        {formationLayout.map((row, rowIndex) => (
          <React.Fragment key={rowIndex}>
            {row.map((cell, cellIndex) => (
              <div
                key={`${rowIndex}-${cellIndex}`}
                className="flex justify-center"
              >
                {cell === "□" ? (
                  <div className="w-5 h-5 bg-green-500/80 dark:bg-green-600/80 rounded-full flex items-center justify-center text-[10px] text-white">
                    {rowIndex + 1}
                  </div>
                ) : (
                  <div className="w-5 h-5"></div>
                )}
              </div>
            ))}
          </React.Fragment>
        ))}
      </div>
    );
  };

  if (isLoading && !selectedClub && clubs.length === 0) {
    return (
      <div className="flex flex-col items-center justify-center h-screen">
        <div className="relative w-20 h-20">
          <div className="absolute inset-0 bg-blue-500 rounded-full blur-md opacity-50 animate-pulse"></div>
          <div className="relative flex items-center justify-center w-full h-full">
            <Loader2 className="h-10 w-10 animate-spin text-blue-600" />
          </div>
        </div>
        <span className="mt-6 text-xl font-medium text-gray-700 dark:text-gray-300">
          Lade Mannschaftsdaten...
        </span>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-blue-50 to-sky-50 dark:from-gray-900 dark:via-indigo-950 dark:to-blue-950 p-4 md:p-8 overflow-hidden relative">
      {/* Background decorative elements */}
      <div className="absolute top-0 left-0 w-full h-full overflow-hidden z-0">
        <div className="absolute top-10 left-10 w-64 h-64 bg-purple-400/20 dark:bg-purple-600/10 rounded-full blur-3xl"></div>
        <div className="absolute bottom-10 right-10 w-80 h-80 bg-blue-400/20 dark:bg-blue-600/10 rounded-full blur-3xl"></div>
        <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-96 h-96 bg-sky-400/10 dark:bg-sky-600/5 rounded-full blur-3xl"></div>
      </div>

      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="container mx-auto max-w-7xl relative z-10"
      >
        <div className="backdrop-blur-xl bg-white/70 dark:bg-gray-900/70 rounded-2xl shadow-2xl overflow-hidden border border-white/20 dark:border-gray-800/50">
          {/* Header */}
          <div className="relative overflow-hidden">
            {/* Background elements */}
            <div className="absolute inset-0 bg-gradient-to-r from-blue-600 via-indigo-600 to-violet-600"></div>
            <div className="absolute inset-0 bg-[url('/placeholder.svg?height=200&width=200')] opacity-5 bg-repeat"></div>
            <div className="absolute top-0 right-0 w-96 h-96 bg-white/10 rounded-full -translate-y-1/2 translate-x-1/2 blur-3xl"></div>
            <div className="absolute bottom-0 left-0 w-64 h-64 bg-indigo-500/20 rounded-full translate-y-1/2 -translate-x-1/2 blur-3xl"></div>

            {/* Content */}
            <div className="relative z-10 px-8 py-10">
              <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-8">
                <div className="flex-1">
                  <motion.div
                    initial={{ opacity: 0, y: -20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5 }}
                    className="flex items-center gap-4 mb-6"
                  >
                    <div className="relative w-12 h-12">
                      <div className="absolute inset-0 bg-white rounded-full blur-md opacity-20"></div>
                      <div className="relative flex items-center justify-center w-full h-full">
                        <Shield className="h-8 w-8 text-white" />
                      </div>
                    </div>
                    <h1 className="text-3xl font-bold tracking-tight text-white">
                      Mannschaftsverwaltung
                    </h1>
                  </motion.div>
                  <motion.div
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5, delay: 0.1 }}
                    className="w-full max-w-md"
                  >
                    <Select
                      onValueChange={(value) => handleClubChange(value)}
                      value={selectedClub?.id.toString() || ""}
                    >
                      <SelectTrigger className="bg-white/10 border-white/20 text-white h-14 rounded-xl backdrop-blur-md">
                        <div className="flex items-center">
                          {selectedClub ? (
                            <div className="flex items-center gap-3">
                              <div className="bg-white/20 w-8 h-8 rounded-full flex items-center justify-center">
                                <Users className="h-4 w-4" />
                              </div>
                              <SelectValue placeholder="Mannschaft auswählen" />
                            </div>
                          ) : (
                            <SelectValue placeholder="Mannschaft auswählen" />
                          )}
                        </div>
                      </SelectTrigger>
                      <SelectContent className="bg-white/90 dark:bg-gray-800/90 backdrop-blur-md border-white/20 dark:border-gray-700/50">
                        {clubs.map((club) => (
                          <SelectItem key={club.id} value={club.id.toString()}>
                            <div className="flex items-center gap-3">
                              <div className="bg-blue-100 dark:bg-blue-900/30 w-8 h-8 rounded-full flex items-center justify-center">
                                <Shield className="h-4 w-4 text-blue-600 dark:text-blue-400" />
                              </div>
                              {club.name}
                            </div>
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </motion.div>
                </div>

                {selectedClub && (
                  <motion.div
                    initial={{ opacity: 0, scale: 0.9 }}
                    animate={{ opacity: 1, scale: 1 }}
                    transition={{ duration: 0.5 }}
                    className="flex items-center gap-6 bg-white/10 p-5 rounded-2xl backdrop-blur-md border border-white/20"
                  >
                    <div className="flex flex-col items-center justify-center">
                      <div className="relative w-14 h-14">
                        <div className="absolute inset-0 bg-yellow-400 rounded-full blur-md opacity-70"></div>
                        <div className="relative bg-gradient-to-br from-yellow-300 to-yellow-500 rounded-full p-3 flex items-center justify-center">
                          <Trophy className="h-7 w-7 text-yellow-900" />
                        </div>
                      </div>
                      <p className="text-xs font-medium text-white/80 mt-1">
                        Team Rating
                      </p>
                    </div>

                    <div>
                      <div className="flex items-center gap-3">
                        <div className="relative">
                          <div
                            className={`absolute inset-0 bg-gradient-to-r ${getRatingColor(
                              calculateTeamRating(selectedClub)
                            )} rounded-xl blur opacity-70`}
                          ></div>
                          <Badge
                            className={`relative bg-gradient-to-r ${getRatingColor(
                              calculateTeamRating(selectedClub)
                            )} text-white font-bold px-4 py-2 text-2xl rounded-xl border-none`}
                          >
                            {calculateTeamRating(selectedClub)}
                          </Badge>
                        </div>
                        <div className="flex flex-col">
                          <div className="flex items-center text-white mb-1">
                            <Zap className="h-4 w-4 mr-1 text-yellow-300" />
                            <span className="font-medium">
                              {selectedClub.spieler.length} Spieler
                            </span>
                          </div>
                          <div className="flex">
                            {[1, 2, 3, 4, 5].map((_, i) => (
                              <Star
                                key={i}
                                className={`h-5 w-5 ${
                                  i <
                                  Math.floor(
                                    calculateTeamRating(selectedClub) / 20
                                  )
                                    ? "text-yellow-300 fill-yellow-300"
                                    : "text-white/30"
                                }`}
                              />
                            ))}
                          </div>
                        </div>
                      </div>
                    </div>
                  </motion.div>
                )}
              </div>
            </div>
          </div>

          {selectedClub ? (
            <motion.div
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              transition={{ duration: 0.3 }}
              className="p-6 md:p-8"
            >
              <Tabs defaultValue="players" className="w-full">
                <TabsList className="grid w-full grid-cols-2 p-1 bg-white/50 dark:bg-gray-800/50 backdrop-blur-md rounded-xl">
                  <TabsTrigger
                    value="players"
                    className="rounded-lg data-[state=active]:bg-white dark:data-[state=active]:bg-gray-700 data-[state=active]:shadow-md transition-all duration-200"
                  >
                    <Users className="h-4 w-4 mr-2" />
                    Spielerverwaltung
                  </TabsTrigger>
                  <TabsTrigger
                    value="details"
                    className="rounded-lg data-[state=active]:bg-white dark:data-[state=active]:bg-gray-700 data-[state=active]:shadow-md transition-all duration-200"
                  >
                    <Trophy className="h-4 w-4 mr-2" />
                    Mannschaftsdetails
                  </TabsTrigger>
                </TabsList>

                <TabsContent value="players" className="mt-8">
                  <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
                    <motion.div
                      initial={{ opacity: 0, x: -20 }}
                      animate={{ opacity: 1, x: 0 }}
                      transition={{ duration: 0.3, delay: 0.1 }}
                      className="bg-white/80 dark:bg-gray-800/80 backdrop-blur-md p-6 rounded-2xl border border-white/50 dark:border-gray-700/50 shadow-xl"
                    >
                      <h3 className="text-lg font-semibold mb-5 flex items-center">
                        <div className="bg-blue-500/10 dark:bg-blue-500/20 p-2 rounded-lg mr-3">
                          <UserPlus className="h-5 w-5 text-blue-600 dark:text-blue-400" />
                        </div>
                        Verfügbare Spieler
                      </h3>
                      <SpielerTable
                        spieler={availablePlayers}
                        onPlayerAction={(player) =>
                          handlePlayerTransfer(player.id, true)
                        }
                        actionType="add"
                        sortConfig={sortConfig}
                        setSortConfig={setSortConfig}
                        buttonText="→ Zum Team"
                      />
                    </motion.div>

                    <motion.div
                      initial={{ opacity: 0, x: 20 }}
                      animate={{ opacity: 1, x: 0 }}
                      transition={{ duration: 0.3, delay: 0.2 }}
                      className="bg-white/80 dark:bg-gray-800/80 backdrop-blur-md p-6 rounded-2xl border border-white/50 dark:border-gray-700/50 shadow-xl"
                    >
                      <h3 className="text-lg font-semibold mb-5 flex items-center">
                        <div className="bg-indigo-500/10 dark:bg-indigo-500/20 p-2 rounded-lg mr-3">
                          <Shield className="h-5 w-5 text-indigo-600 dark:text-indigo-400" />
                        </div>
                        Mannschaftskader
                      </h3>
                      <SpielerTable
                        spieler={teamPlayers}
                        onPlayerAction={(player) =>
                          handlePlayerTransfer(player.id, false)
                        }
                        actionType="remove"
                        sortConfig={sortConfig}
                        setSortConfig={setSortConfig}
                        buttonText="← Aus Team"
                      />
                    </motion.div>
                  </div>
                </TabsContent>

                <TabsContent value="details" className="mt-8">
                  <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                    <motion.div
                      initial={{ opacity: 0, y: 20 }}
                      animate={{ opacity: 1, y: 0 }}
                      transition={{ duration: 0.5 }}
                      className="md:col-span-2"
                    >
                      <Card className="overflow-hidden border-0 shadow-xl bg-white/80 dark:bg-gray-800/80 backdrop-blur-md">
                        <div className="bg-gradient-to-r from-blue-500/10 to-indigo-500/10 dark:from-blue-500/20 dark:to-indigo-500/20 p-5 border-b border-white/20 dark:border-gray-700/50">
                          <h3 className="text-lg font-semibold flex items-center">
                            <div className="bg-blue-100 dark:bg-blue-900/30 p-2 rounded-lg mr-3">
                              <Shield className="h-5 w-5 text-blue-600 dark:text-blue-400" />
                            </div>
                            Mannschaftskonfiguration
                          </h3>
                        </div>
                        <CardContent className="pt-6">
                          <div className="space-y-6">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                              <div className="space-y-2">
                                <label
                                  htmlFor="trainer"
                                  className="flex items-center text-sm font-medium"
                                >
                                  <Briefcase className="h-4 w-4 mr-1 text-gray-400" />
                                  Trainer
                                </label>
                                <div className="relative">
                                  <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                    <Users className="h-4 w-4 text-gray-400" />
                                  </div>
                                  <Input
                                    id="trainer"
                                    value={selectedClub.trainer}
                                    onChange={(e) =>
                                      setSelectedClub({
                                        ...selectedClub,
                                        trainer: e.target.value,
                                      })
                                    }
                                    placeholder="Trainername eingeben"
                                    className="pl-10 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md focus:ring-2 focus:ring-blue-500/20"
                                  />
                                </div>
                              </div>

                              <div className="space-y-2">
                                <label
                                  htmlFor="liga"
                                  className="flex items-center text-sm font-medium"
                                >
                                  <Trophy className="h-4 w-4 mr-1 text-gray-400" />
                                  Liga
                                </label>
                                <Select
                                  value={
                                    selectedClub.ligaString || "BUNDESLIGA"
                                  }
                                  onValueChange={(value) =>
                                    setSelectedClub({
                                      ...selectedClub,
                                      ligaString: value,
                                    })
                                  }
                                >
                                  <SelectTrigger
                                    id="liga"
                                    className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md focus:ring-2 focus:ring-blue-500/20"
                                  >
                                    <SelectValue placeholder="Liga auswählen" />
                                  </SelectTrigger>
                                  <SelectContent className="bg-white/90 dark:bg-gray-800/90 backdrop-blur-md border-white/20 dark:border-gray-700/50">
                                    <SelectItem value="BUNDESLIGA">
                                      <div className="flex items-center gap-2">
                                        <Trophy className="h-4 w-4 text-yellow-500" />
                                        Bundesliga
                                      </div>
                                    </SelectItem>
                                    <SelectItem value="ZWEITE_BUNDESLIGA">
                                      <div className="flex items-center gap-2">
                                        <Trophy className="h-4 w-4 text-blue-500" />
                                        2. Bundesliga
                                      </div>
                                    </SelectItem>
                                    <SelectItem value="DRITTE_LIGA">
                                      <div className="flex items-center gap-2">
                                        <Trophy className="h-4 w-4 text-green-500" />
                                        3. Liga
                                      </div>
                                    </SelectItem>
                                  </SelectContent>
                                </Select>
                              </div>
                            </div>

                            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                              <div className="space-y-2">
                                <label
                                  htmlFor="formation"
                                  className="flex items-center text-sm font-medium"
                                >
                                  <Users className="h-4 w-4 mr-1 text-gray-400" />
                                  Formation
                                </label>
                                <Select
                                  value={
                                    selectedClub.formationString || "4-4-2"
                                  }
                                  onValueChange={(value) =>
                                    setSelectedClub({
                                      ...selectedClub,
                                      formationString: value,
                                    })
                                  }
                                >
                                  <SelectTrigger
                                    id="formation"
                                    className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md focus:ring-2 focus:ring-blue-500/20"
                                  >
                                    <SelectValue placeholder="Formation auswählen" />
                                  </SelectTrigger>
                                  <SelectContent className="bg-white/90 dark:bg-gray-800/90 backdrop-blur-md border-white/20 dark:border-gray-700/50">
                                    <SelectItem value="4-4-2">4-4-2</SelectItem>
                                    <SelectItem value="4-3-3">4-3-3</SelectItem>
                                    <SelectItem value="3-5-2">3-5-2</SelectItem>
                                  </SelectContent>
                                </Select>
                              </div>

                              <div className="relative overflow-hidden rounded-xl">
                                <div className="absolute inset-0 bg-gradient-to-br from-green-400/20 to-emerald-500/20 dark:from-green-500/10 dark:to-emerald-600/10"></div>
                                <div className="absolute inset-0 bg-[url('/placeholder.svg?height=100&width=100')] opacity-5 bg-repeat"></div>
                                <div className="relative p-4 flex items-center justify-center border border-green-200/50 dark:border-green-900/30 rounded-xl backdrop-blur-sm">
                                  {getFormationPreview(
                                    selectedClub.formationString || "4-4-2"
                                  )}
                                </div>
                              </div>
                            </div>

                            <Separator className="my-6 bg-white/20 dark:bg-gray-700/50" />

                            <div className="flex gap-4">
                              <Button
                                onClick={handleUpdateTeam}
                                className="flex-1 bg-gradient-to-r from-blue-500 to-indigo-600 hover:from-blue-600 hover:to-indigo-700 shadow-lg h-12 rounded-xl relative overflow-hidden group"
                              >
                                <div className="absolute inset-0 bg-white/20 opacity-0 group-hover:opacity-20 transition-opacity"></div>
                                <div className="absolute inset-0 bg-[url('/placeholder.svg?height=50&width=50')] opacity-5 bg-repeat"></div>
                                <Save className="h-5 w-5 mr-2" />
                                Mannschaft speichern
                              </Button>

                              <Button
                                onClick={handleFormationCheck}
                                className="bg-gradient-to-r from-green-500 to-emerald-600 hover:from-green-600 hover:to-emerald-700 shadow-lg h-12 rounded-xl relative overflow-hidden group"
                              >
                                <div className="absolute inset-0 bg-white/20 opacity-0 group-hover:opacity-20 transition-opacity"></div>
                                <CheckCircle className="h-5 w-5 mr-2" />
                                Formation prüfen
                              </Button>
                            </div>
                          </div>
                        </CardContent>
                      </Card>
                    </motion.div>

                    <motion.div
                      initial={{ opacity: 0, y: 20 }}
                      animate={{ opacity: 1, y: 0 }}
                      transition={{ duration: 0.5, delay: 0.2 }}
                    >
                      <Card className="overflow-hidden border-0 shadow-xl bg-white/80 dark:bg-gray-800/80 backdrop-blur-md">
                        <div className="bg-gradient-to-r from-indigo-500/10 to-purple-500/10 dark:from-indigo-500/20 dark:to-purple-500/20 p-5 border-b border-white/20 dark:border-gray-700/50">
                          <h3 className="text-lg font-semibold flex items-center">
                            <div className="bg-indigo-100 dark:bg-indigo-900/30 p-2 rounded-lg mr-3">
                              <Award className="h-5 w-5 text-indigo-600 dark:text-indigo-400" />
                            </div>
                            Mannschaftsstatistik
                          </h3>
                        </div>
                        <CardContent className="pt-6">
                          <div className="space-y-5">
                            <div className="relative overflow-hidden rounded-xl">
                              <div className="absolute inset-0 bg-gradient-to-r from-blue-400/5 to-blue-500/10 dark:from-blue-400/10 dark:to-blue-500/20"></div>
                              <div className="relative p-4 flex items-center gap-4 border border-blue-100/50 dark:border-blue-900/30 rounded-xl backdrop-blur-sm">
                                <div className="relative w-12 h-12">
                                  <div className="absolute inset-0 bg-blue-500/20 rounded-full blur-md"></div>
                                  <div className="relative flex items-center justify-center w-full h-full bg-gradient-to-br from-blue-400 to-blue-600 rounded-full">
                                    <Users className="h-6 w-6 text-white" />
                                  </div>
                                </div>
                                <div>
                                  <p className="text-sm text-gray-500 dark:text-gray-400">
                                    Kadergröße
                                  </p>
                                  <div className="flex items-center">
                                    <p className="font-bold text-2xl bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
                                      {selectedClub.spieler.length}
                                    </p>
                                    <p className="ml-1 font-medium">Spieler</p>
                                  </div>
                                </div>
                              </div>
                            </div>

                            <div className="relative overflow-hidden rounded-xl">
                              <div className="absolute inset-0 bg-gradient-to-r from-yellow-400/5 to-yellow-500/10 dark:from-yellow-400/10 dark:to-yellow-500/20"></div>
                              <div className="relative p-4 flex items-center gap-4 border border-yellow-100/50 dark:border-yellow-900/30 rounded-xl backdrop-blur-sm">
                                <div className="relative w-12 h-12">
                                  <div className="absolute inset-0 bg-yellow-500/20 rounded-full blur-md"></div>
                                  <div className="relative flex items-center justify-center w-full h-full bg-gradient-to-br from-yellow-400 to-yellow-600 rounded-full">
                                    <Trophy className="h-6 w-6 text-white" />
                                  </div>
                                </div>
                                <div>
                                  <p className="text-sm text-gray-500 dark:text-gray-400">
                                    Liga
                                  </p>
                                  <div className="flex items-center">
                                    <p className="font-bold text-xl bg-gradient-to-r from-yellow-500 to-amber-600 bg-clip-text text-transparent">
                                      {selectedClub.ligaString === "BUNDESLIGA"
                                        ? "Bundesliga"
                                        : selectedClub.ligaString ===
                                          "ZWEITE_BUNDESLIGA"
                                        ? "2. Bundesliga"
                                        : "3. Liga"}
                                    </p>
                                  </div>
                                </div>
                              </div>
                            </div>

                            <div className="relative overflow-hidden rounded-xl">
                              <div className="absolute inset-0 bg-gradient-to-r from-green-400/5 to-green-500/10 dark:from-green-400/10 dark:to-green-500/20"></div>
                              <div className="relative p-4 flex items-center gap-4 border border-green-100/50 dark:border-green-900/30 rounded-xl backdrop-blur-sm">
                                <div className="relative w-12 h-12">
                                  <div className="absolute inset-0 bg-green-500/20 rounded-full blur-md"></div>
                                  <div className="relative flex items-center justify-center w-full h-full bg-gradient-to-br from-green-400 to-green-600 rounded-full">
                                    <Users className="h-6 w-6 text-white" />
                                  </div>
                                </div>
                                <div>
                                  <p className="text-sm text-gray-500 dark:text-gray-400">
                                    Formation
                                  </p>
                                  <div className="flex items-center">
                                    <p className="font-bold text-xl bg-gradient-to-r from-green-500 to-emerald-600 bg-clip-text text-transparent">
                                      {selectedClub.formationString || "4-4-2"}
                                    </p>
                                  </div>
                                </div>
                              </div>
                            </div>

                            <Separator className="my-4 bg-white/20 dark:bg-gray-700/50" />

                            <div className="relative overflow-hidden rounded-xl">
                              <div className="absolute inset-0 bg-gradient-to-r from-gray-400/5 to-gray-500/10 dark:from-gray-400/10 dark:to-gray-500/20"></div>
                              <div className="relative p-4 flex items-center justify-center gap-2 border border-gray-100/50 dark:border-gray-800/50 rounded-xl backdrop-blur-sm">
                                <Calendar className="h-4 w-4 text-gray-400" />
                                <p className="text-sm text-gray-500 dark:text-gray-400">
                                  Zuletzt aktualisiert:{" "}
                                  {new Date().toLocaleDateString()}
                                </p>
                              </div>
                            </div>
                          </div>
                        </CardContent>
                      </Card>
                    </motion.div>
                  </div>
                </TabsContent>
              </Tabs>
            </motion.div>
          ) : (
            <motion.div
              initial={{ opacity: 0, scale: 0.95 }}
              animate={{ opacity: 1, scale: 1 }}
              transition={{ duration: 0.5 }}
              className="p-16 text-center"
            >
              <div className="max-w-md mx-auto">
                <div className="relative w-28 h-28 mx-auto mb-8">
                  <div className="absolute inset-0 bg-blue-500 rounded-full blur-xl opacity-20 animate-pulse"></div>
                  <div className="absolute inset-0 bg-gradient-to-br from-blue-400 to-indigo-600 rounded-full opacity-80"></div>
                  <div className="absolute inset-0 flex items-center justify-center">
                    <Shield className="h-12 w-12 text-white" />
                  </div>
                </div>
                <h3 className="text-3xl font-bold text-gray-800 dark:text-gray-100 mb-4">
                  Mannschaft auswählen
                </h3>
                <p className="text-gray-500 dark:text-gray-400 mb-8 text-lg">
                  Wählen Sie eine Mannschaft aus dem Dropdown-Menü oben, um
                  Spieler zu verwalten
                </p>
              </div>
            </motion.div>
          )}
        </div>
      </motion.div>
    </div>
  );
};

export default MannschaftManagementPanel;
