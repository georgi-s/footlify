"use client";

import React from "react";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import {
  getMannschaften,
  createMannschaft,
  updateMannschaft,
  deleteMannschaft,
} from "../../services/mannschaftService";
import { MannschaftDTO, createDefaultMannschaftDTO } from "../../types/MannschaftDTO";
import { Formation, Liga } from "../../types/types";
import { useToast } from "../ui/use-toast";
import { Button } from "../ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "../ui/dialog";
import { Input } from "../ui/input";
import { Label } from "../ui/label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../ui/table";
import { Badge } from "../ui/badge";
import {
  Shield,
  Trophy,
  Plus,
  Edit,
  Trash2,
  Loader2,
  CheckCircle,
  AlertTriangle,
  Briefcase,
  ChevronRight,
  Search,
  RefreshCw,
} from "lucide-react";
import ThemeToggle from "../theme-toggle";

const MannschaftList: React.FC = () => {
  const { toast } = useToast();
  const navigate = useNavigate();
  const [mannschaften, setMannschaften] = useState<MannschaftDTO[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState("");
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);
  const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);
  const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
  const [currentMannschaft, setCurrentMannschaft] =
    useState<MannschaftDTO | null>(null);
  const [newMannschaft, setNewMannschaft] = useState<Partial<MannschaftDTO>>(createDefaultMannschaftDTO());

  useEffect(() => {
    fetchMannschaften();
  }, []);

  const fetchMannschaften = async () => {
    setIsLoading(true);
    try {
      const data = await getMannschaften();
      setMannschaften(data);
    } catch (error) {
      console.error("Fehler beim Laden der Mannschaften:", error);
      toast({
        title: "Fehler beim Laden",
        description: "Die Mannschaften konnten nicht geladen werden.",
        variant: "destructive",
      });
    } finally {
      setIsLoading(false);
    }
  };

  const handleCreate = async () => {
    try {
      const created = await createMannschaft(newMannschaft);
      toast({
        title: "Mannschaft erstellt",
        description: `${newMannschaft.name} wurde erfolgreich erstellt.`,
      });
      fetchMannschaften();
      setIsCreateDialogOpen(false);
      setNewMannschaft(createDefaultMannschaftDTO());
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
      toast({
        title: "Fehler beim Erstellen",
        description: "Die Mannschaft konnte nicht erstellt werden.",
        variant: "destructive",
      });
    }
  };

  const handleUpdate = async () => {
    if (!currentMannschaft) return;

    try {
      await updateMannschaft(currentMannschaft.id!, currentMannschaft);
      toast({
        title: "Mannschaft aktualisiert",
        description: `${currentMannschaft.name} wurde erfolgreich aktualisiert.`,
      });
      fetchMannschaften();
      setIsEditDialogOpen(false);
    } catch (error) {
      console.error("Fehler beim Aktualisieren:", error);
      toast({
        title: "Fehler beim Aktualisieren",
        description: "Die Mannschaft konnte nicht aktualisiert werden.",
        variant: "destructive",
      });
    }
  };

  const handleDelete = async () => {
    if (!currentMannschaft) return;

    try {
      await deleteMannschaft(currentMannschaft.id!);
      toast({
        title: "Mannschaft gelöscht",
        description: `${currentMannschaft.name} wurde erfolgreich gelöscht.`,
      });
      fetchMannschaften();
      setIsDeleteDialogOpen(false);
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
      toast({
        title: "Fehler beim Löschen",
        description: "Die Mannschaft konnte nicht gelöscht werden.",
        variant: "destructive",
      });
    }
  };

  // Filter mannschaften based on search term
  const filteredMannschaften = mannschaften.filter(
    (m) =>
      m.name?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      m.trainer?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // Get formation name
  const getFormationName = (code: string) => {
    const formationMap: Record<string, string> = {
      f442: "4-4-2",
      f433: "4-3-3",
      f352: "3-5-2",
      f343: "3-4-3",
      f532: "5-3-2",
    };
    return formationMap[code] || code;
  };

  // Get liga name
  const getLigaName = (code: string) => {
    const ligaMap: Record<string, string> = {
      Bundesliga1: "Bundesliga",
      Bundesliga2: "2. Bundesliga",
      Bundesliga3: "3. Liga",
    };
    return ligaMap[code] || code;
  };

  // Get liga badge color
  const getLigaBadgeColor = (liga: string) => {
    switch (liga) {
      case "Bundesliga1":
        return "bg-red-100 text-red-800 dark:bg-red-900/30 dark:text-red-300";
      case "Bundesliga2":
        return "bg-blue-100 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300";
      case "Bundesliga3":
        return "bg-green-100 text-green-800 dark:bg-green-900/30 dark:text-green-300";
      default:
        return "bg-gray-100 text-gray-800 dark:bg-gray-900/30 dark:text-gray-300";
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-blue-50 to-sky-50 dark:from-gray-900 dark:via-indigo-950 dark:to-blue-950 p-4 md:p-8 overflow-hidden relative">
      {/* Background decorative elements */}
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
                        <Trophy className="h-8 w-8 text-white" />
                      </div>
                    </div>
                    <h1 className="text-3xl font-bold tracking-tight text-white">
                      Mannschaftsverwaltung
                    </h1>
                  </motion.div>
                  <motion.p
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5, delay: 0.1 }}
                    className="text-white/80 max-w-2xl"
                  >
                    Verwalten Sie Ihre Mannschaften, erstellen Sie neue Teams
                    und bearbeiten Sie bestehende Einträge.
                  </motion.p>
                </div>

                <motion.div
                  initial={{ opacity: 0, scale: 0.9 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ duration: 0.5 }}
                  className="flex items-center gap-3"
                >
                  <Button
                    onClick={() => fetchMannschaften()}
                    variant="outline"
                    className="bg-white/10 border-white/20 text-white hover:bg-white/20 hover:text-white"
                  >
                    <RefreshCw className="h-4 w-4 mr-2" />
                    Aktualisieren
                  </Button>

                  <Dialog
                    open={isCreateDialogOpen}
                    onOpenChange={setIsCreateDialogOpen}
                  >
                    <DialogTrigger asChild>
                      <Button className="bg-gradient-to-r from-green-500 to-emerald-600 hover:from-green-600 hover:to-emerald-700 text-white border-0 shadow-lg relative overflow-hidden group">
                        <div className="absolute inset-0 bg-white/20 opacity-0 group-hover:opacity-20 transition-opacity"></div>
                        <Plus className="h-4 w-4 mr-2" />
                        Neue Mannschaft
                      </Button>
                    </DialogTrigger>
                    <DialogContent className="bg-white/90 dark:bg-gray-900/90 backdrop-blur-xl border border-white/20 dark:border-gray-800/50 shadow-2xl">
                      <DialogHeader>
                        <DialogTitle className="text-xl font-bold flex items-center">
                          <div className="bg-green-100 dark:bg-green-900/30 p-2 rounded-lg mr-3">
                            <Plus className="h-5 w-5 text-green-600 dark:text-green-400" />
                          </div>
                          Neue Mannschaft erstellen
                        </DialogTitle>
                        <DialogDescription>
                          Füllen Sie die Felder aus, um eine neue Mannschaft zu
                          erstellen.
                        </DialogDescription>
                      </DialogHeader>
                      <div className="grid gap-4 py-4">
                        <div className="grid grid-cols-4 items-center gap-4">
                          <Label htmlFor="name" className="text-right">
                            Name
                          </Label>
                          <Input
                            id="name"
                            value={newMannschaft.name}
                            onChange={(e) =>
                              setNewMannschaft({
                                ...newMannschaft,
                                name: e.target.value,
                              })
                            }
                            className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                          />
                        </div>
                        <div className="grid grid-cols-4 items-center gap-4">
                          <Label htmlFor="trainer" className="text-right">
                            Trainer
                          </Label>
                          <Input
                            id="trainer"
                            value={newMannschaft.trainer}
                            onChange={(e) =>
                              setNewMannschaft({
                                ...newMannschaft,
                                trainer: e.target.value,
                              })
                            }
                            className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                          />
                        </div>
                        <div className="grid grid-cols-4 items-center gap-4">
                          <Label htmlFor="formation" className="text-right">
                            Formation
                          </Label>
                          <Select
                            value={newMannschaft.formation}
                            onValueChange={(value: Formation) =>
                              setNewMannschaft({
                                ...newMannschaft,
                                formation: value,
                              })
                            }
                          >
                            <SelectTrigger
                              id="formation"
                              className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                            >
                              <SelectValue placeholder="Formation auswählen" />
                            </SelectTrigger>
                            <SelectContent className="bg-white/90 dark:bg-gray-800/90 backdrop-blur-md">
                              <SelectItem value={Formation.f442}>4-4-2</SelectItem>
                              <SelectItem value={Formation.f433}>4-3-3</SelectItem>
                              <SelectItem value={Formation.f343}>3-4-3</SelectItem>
                              <SelectItem value={Formation.f532}>5-3-2</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                        <div className="grid grid-cols-4 items-center gap-4">
                          <Label htmlFor="liga" className="text-right">
                            Liga
                          </Label>
                          <Select
                            value={newMannschaft.liga}
                            onValueChange={(value: Liga) =>
                              setNewMannschaft({
                                ...newMannschaft,
                                liga: value,
                              })
                            }
                          >
                            <SelectTrigger
                              id="liga"
                              className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                            >
                              <SelectValue placeholder="Liga auswählen" />
                            </SelectTrigger>
                            <SelectContent className="bg-white/90 dark:bg-gray-800/90 backdrop-blur-md">
                              <SelectItem value={Liga.Bundesliga1}>
                                Bundesliga
                              </SelectItem>
                              <SelectItem value={Liga.Bundesliga2}>
                                2. Bundesliga
                              </SelectItem>
                              <SelectItem value={Liga.Bundesliga3}>
                                3. Liga
                              </SelectItem>
                              <SelectItem value={Liga.RegionalligaWest}>
                                Regionalliga West
                              </SelectItem>
                              <SelectItem value={Liga.OberligaWestfalen}>
                                Oberliga Westfalen
                              </SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                      </div>
                      <DialogFooter>
                        <Button
                          variant="outline"
                          onClick={() => setIsCreateDialogOpen(false)}
                          className="border-gray-200 dark:border-gray-700"
                        >
                          Abbrechen
                        </Button>
                        <Button
                          onClick={handleCreate}
                          className="bg-gradient-to-r from-green-500 to-emerald-600 hover:from-green-600 hover:to-emerald-700 text-white border-0"
                        >
                          <CheckCircle className="h-4 w-4 mr-2" />
                          Erstellen
                        </Button>
                      </DialogFooter>
                    </DialogContent>
                  </Dialog>
                </motion.div>
              </div>
            </div>
          </div>

          {/* Main content */}
          <div className="p-6 md:p-8">
            <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-4 mb-8">
              <div className="relative w-full max-w-md">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <Search className="h-4 w-4 text-gray-400" />
                </div>
                <Input
                  type="text"
                  placeholder="Mannschaft oder Trainer suchen..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="pl-10 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md focus:ring-2 focus:ring-blue-500/20"
                />
              </div>

              <div className="flex items-center gap-2">
                <Badge
                  variant="outline"
                  className="bg-white/50 dark:bg-gray-800/50 backdrop-blur-md"
                >
                  {filteredMannschaften.length} Mannschaften
                </Badge>
              </div>
            </div>

            {isLoading ? (
              <div className="flex flex-col items-center justify-center py-16">
                <div className="relative w-16 h-16 mb-4">
                  <div className="absolute inset-0 bg-blue-500 rounded-full blur-md opacity-50 animate-pulse"></div>
                  <div className="relative flex items-center justify-center w-full h-full">
                    <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
                  </div>
                </div>
                <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
                  Lade Mannschaften...
                </p>
              </div>
            ) : filteredMannschaften.length === 0 ? (
              <div className="flex flex-col items-center justify-center py-16 bg-white/50 dark:bg-gray-800/50 backdrop-blur-md rounded-xl border border-white/20 dark:border-gray-700/50">
                <div className="relative w-16 h-16 mb-4">
                  <div className="absolute inset-0 bg-gray-200 dark:bg-gray-700 rounded-full blur-md opacity-50"></div>
                  <div className="relative flex items-center justify-center w-full h-full">
                    <AlertTriangle className="h-8 w-8 text-gray-400 dark:text-gray-500" />
                  </div>
                </div>
                <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
                  Keine Mannschaften gefunden
                </p>
                <p className="text-gray-500 dark:text-gray-400 mt-2">
                  {searchTerm
                    ? "Versuchen Sie einen anderen Suchbegriff."
                    : "Erstellen Sie eine neue Mannschaft, um zu beginnen."}
                </p>
                {searchTerm && (
                  <Button
                    variant="outline"
                    onClick={() => setSearchTerm("")}
                    className="mt-4"
                  >
                    Suche zurücksetzen
                  </Button>
                )}
              </div>
            ) : (
              <div className="bg-white/80 dark:bg-gray-800/80 backdrop-blur-md rounded-xl border border-white/50 dark:border-gray-700/50 shadow-xl overflow-hidden">
                <Table>
                  <TableHeader className="bg-white/30 dark:bg-gray-800/30 backdrop-blur-md">
                    <TableRow className="hover:bg-transparent border-b border-white/20 dark:border-gray-700/50">
                      <TableHead className="w-[50px] font-semibold">
                        ID
                      </TableHead>
                      <TableHead className="font-semibold">Name</TableHead>
                      <TableHead className="font-semibold">Trainer</TableHead>
                      <TableHead className="font-semibold">Formation</TableHead>
                      <TableHead className="font-semibold">Liga</TableHead>
                      <TableHead className="text-right font-semibold">
                        Aktionen
                      </TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {filteredMannschaften.map((mannschaft, index) => (
                      <motion.tr
                        key={mannschaft.id ?? Math.random()}
                        initial={{ opacity: 0, y: 10 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.3, delay: index * 0.05 }}
                        className="hover:bg-white/40 dark:hover:bg-gray-800/40 transition-colors border-b border-white/10 dark:border-gray-700/30"
                      >
                        <TableCell className="font-medium">
                          <div className="relative w-10 h-10">
                            <div className="absolute inset-0 bg-blue-500/10 dark:bg-blue-500/20 rounded-full blur-sm"></div>
                            <div className="relative flex items-center justify-center w-full h-full bg-white/30 dark:bg-gray-800/30 backdrop-blur-sm rounded-full border border-white/30 dark:border-gray-700/30">
                              {mannschaft.id}
                            </div>
                          </div>
                        </TableCell>
                        <TableCell>
                          <div className="flex items-center gap-3">
                            <div className="bg-indigo-100 dark:bg-indigo-900/30 w-8 h-8 rounded-full flex items-center justify-center">
                              <Shield className="h-4 w-4 text-indigo-600 dark:text-indigo-400" />
                            </div>
                            <span className="font-semibold">
                              {mannschaft.name}
                            </span>
                          </div>
                        </TableCell>
                        <TableCell>
                          <div className="flex items-center gap-2">
                            <Briefcase className="h-4 w-4 text-gray-400" />
                            {mannschaft.trainer}
                          </div>
                        </TableCell>
                        <TableCell>
                          <Badge
                            variant="outline"
                            className="bg-blue-100/50 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300"
                          >
                            {getFormationName(mannschaft.formation || "")}
                          </Badge>
                        </TableCell>
                        <TableCell>
                          <Badge
                            variant="outline"
                            className={getLigaBadgeColor(mannschaft.liga || "")}
                          >
                            {getLigaName(mannschaft.liga || "")}
                          </Badge>
                        </TableCell>
                        <TableCell className="text-right">
                          <div className="flex items-center justify-end gap-2">
                            <Dialog
                              open={
                                isEditDialogOpen &&
                                currentMannschaft?.id === mannschaft.id
                              }
                              onOpenChange={(open) => {
                                setIsEditDialogOpen(open);
                                if (open) setCurrentMannschaft(mannschaft);
                              }}
                            >
                              <DialogTrigger asChild>
                                <Button
                                  variant="outline"
                                  size="sm"
                                  onClick={() =>
                                    setCurrentMannschaft(mannschaft)
                                  }
                                  className="bg-blue-100/50 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300 border-blue-200 dark:border-blue-800/30 hover:bg-blue-200/50 dark:hover:bg-blue-800/40"
                                >
                                  <Edit className="h-4 w-4" />
                                </Button>
                              </DialogTrigger>
                              <DialogContent className="bg-white/90 dark:bg-gray-900/90 backdrop-blur-xl border border-white/20 dark:border-gray-800/50 shadow-2xl">
                                <DialogHeader>
                                  <DialogTitle className="text-xl font-bold flex items-center">
                                    <div className="bg-blue-100 dark:bg-blue-900/30 p-2 rounded-lg mr-3">
                                      <Edit className="h-5 w-5 text-blue-600 dark:text-blue-400" />
                                    </div>
                                    Mannschaft bearbeiten
                                  </DialogTitle>
                                  <DialogDescription>
                                    Bearbeiten Sie die Daten der Mannschaft.
                                  </DialogDescription>
                                </DialogHeader>
                                {currentMannschaft && (
                                  <div className="grid gap-4 py-4">
                                    <div className="grid grid-cols-4 items-center gap-4">
                                      <Label
                                        htmlFor="edit-name"
                                        className="text-right"
                                      >
                                        Name
                                      </Label>
                                      <Input
                                        id="edit-name"
                                        value={currentMannschaft.name}
                                        onChange={(e) =>
                                          setCurrentMannschaft({
                                            ...currentMannschaft,
                                            name: e.target.value,
                                          })
                                        }
                                        className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                                      />
                                    </div>
                                    <div className="grid grid-cols-4 items-center gap-4">
                                      <Label
                                        htmlFor="edit-trainer"
                                        className="text-right"
                                      >
                                        Trainer
                                      </Label>
                                      <Input
                                        id="edit-trainer"
                                        value={currentMannschaft.trainer}
                                        onChange={(e) =>
                                          setCurrentMannschaft({
                                            ...currentMannschaft,
                                            trainer: e.target.value,
                                          })
                                        }
                                        className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                                      />
                                    </div>
                                    <div className="grid grid-cols-4 items-center gap-4">
                                      <Label
                                        htmlFor="edit-formation"
                                        className="text-right"
                                      >
                                        Formation
                                      </Label>
                                      <Select
                                        value={currentMannschaft.formation}
                                        onValueChange={(value: Formation) =>
                                          setCurrentMannschaft({
                                            ...currentMannschaft,
                                            formation: value as Formation,
                                          })
                                        }
                                      >
                                        <SelectTrigger
                                          id="edit-formation"
                                          className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                                        >
                                          <SelectValue placeholder="Formation auswählen" />
                                        </SelectTrigger>
                                        <SelectContent className="bg-white/90 dark:bg-gray-800/90 backdrop-blur-md">
                                          <SelectItem value={Formation.f442}>
                                            4-4-2
                                          </SelectItem>
                                          <SelectItem value={Formation.f433}>
                                            4-3-3
                                          </SelectItem>
                                          <SelectItem value={Formation.f343}>
                                            3-4-3
                                          </SelectItem>
                                          <SelectItem value={Formation.f532}>
                                            5-3-2
                                          </SelectItem>
                                        </SelectContent>
                                      </Select>
                                    </div>
                                    <div className="grid grid-cols-4 items-center gap-4">
                                      <Label
                                        htmlFor="edit-liga"
                                        className="text-right"
                                      >
                                        Liga
                                      </Label>
                                      <Select
                                        value={currentMannschaft.liga}
                                        onValueChange={(value: Liga) =>
                                          setCurrentMannschaft({
                                            ...currentMannschaft,
                                            liga: value as Liga,
                                          })
                                        }
                                      >
                                        <SelectTrigger
                                          id="edit-liga"
                                          className="col-span-3 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                                        >
                                          <SelectValue placeholder="Liga auswählen" />
                                        </SelectTrigger>
                                        <SelectContent className="bg-white/90 dark:bg-gray-800/90 backdrop-blur-md">
                                          <SelectItem value={Liga.Bundesliga1}>
                                            Bundesliga
                                          </SelectItem>
                                          <SelectItem value={Liga.Bundesliga2}>
                                            2. Bundesliga
                                          </SelectItem>
                                          <SelectItem value="Bundesliga3">
                                            3. Liga
                                          </SelectItem>
                                        </SelectContent>
                                      </Select>
                                    </div>
                                  </div>
                                )}
                                <DialogFooter>
                                  <Button
                                    variant="outline"
                                    onClick={() => setIsEditDialogOpen(false)}
                                    className="border-gray-200 dark:border-gray-700"
                                  >
                                    Abbrechen
                                  </Button>
                                  <Button
                                    onClick={handleUpdate}
                                    className="bg-gradient-to-r from-blue-500 to-indigo-600 hover:from-blue-600 hover:to-indigo-700 text-white border-0"
                                  >
                                    <CheckCircle className="h-4 w-4 mr-2" />
                                    Speichern
                                  </Button>
                                </DialogFooter>
                              </DialogContent>
                            </Dialog>

                            <Dialog
                              open={
                                isDeleteDialogOpen &&
                                currentMannschaft?.id === mannschaft.id
                              }
                              onOpenChange={(open) => {
                                setIsDeleteDialogOpen(open);
                                if (open) setCurrentMannschaft(mannschaft);
                              }}
                            >
                              <DialogTrigger asChild>
                                <Button
                                  variant="outline"
                                  size="sm"
                                  onClick={() =>
                                    setCurrentMannschaft(mannschaft)
                                  }
                                  className="bg-red-100/50 text-red-800 dark:bg-red-900/30 dark:text-red-300 border-red-200 dark:border-red-800/30 hover:bg-red-200/50 dark:hover:bg-red-800/40"
                                >
                                  <Trash2 className="h-4 w-4" />
                                </Button>
                              </DialogTrigger>
                              <DialogContent className="bg-white/90 dark:bg-gray-900/90 backdrop-blur-xl border border-white/20 dark:border-gray-800/50 shadow-2xl">
                                <DialogHeader>
                                  <DialogTitle className="text-xl font-bold flex items-center">
                                    <div className="bg-red-100 dark:bg-red-900/30 p-2 rounded-lg mr-3">
                                      <AlertTriangle className="h-5 w-5 text-red-600 dark:text-red-400" />
                                    </div>
                                    Mannschaft löschen
                                  </DialogTitle>
                                  <DialogDescription>
                                    Sind Sie sicher, dass Sie diese Mannschaft
                                    löschen möchten? Diese Aktion kann nicht
                                    rückgängig gemacht werden.
                                  </DialogDescription>
                                </DialogHeader>
                                {currentMannschaft && (
                                  <div className="py-4">
                                    <div className="bg-red-50 dark:bg-red-900/20 border border-red-100 dark:border-red-800/30 rounded-lg p-4">
                                      <p className="text-red-800 dark:text-red-300 font-medium">
                                        Sie sind dabei, die folgende Mannschaft
                                        zu löschen:
                                      </p>
                                      <div className="mt-2 flex items-center gap-3">
                                        <Shield className="h-5 w-5 text-red-600 dark:text-red-400" />
                                        <span className="font-bold">
                                          {currentMannschaft.name}
                                        </span>
                                        <span className="text-gray-500 dark:text-gray-400">
                                          (Trainer: {currentMannschaft.trainer})
                                        </span>
                                      </div>
                                    </div>
                                  </div>
                                )}
                                <DialogFooter>
                                  <Button
                                    variant="outline"
                                    onClick={() => setIsDeleteDialogOpen(false)}
                                    className="border-gray-200 dark:border-gray-700"
                                  >
                                    Abbrechen
                                  </Button>
                                  <Button
                                    onClick={handleDelete}
                                    variant="destructive"
                                    className="bg-gradient-to-r from-red-500 to-rose-600 hover:from-red-600 hover:to-rose-700 text-white border-0"
                                  >
                                    <Trash2 className="h-4 w-4 mr-2" />
                                    Löschen
                                  </Button>
                                </DialogFooter>
                              </DialogContent>
                            </Dialog>

                            <Button
                              variant="ghost"
                              size="sm"
                              className="text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-200"
                              onClick={() => {
                                // Navigiere zur Mannschaftsdetailseite
                                navigate(`/mannschaften/${mannschaft.id}`);
                              }}
                            >
                              <ChevronRight className="h-4 w-4" />
                            </Button>
                          </div>
                        </TableCell>
                      </motion.tr>
                    ))}
                  </TableBody>
                </Table>
              </div>
            )}
          </div>
        </div>
      </motion.div>
    </div>
  );
};

export default MannschaftList;
