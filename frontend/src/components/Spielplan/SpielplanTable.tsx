"use client";
import React from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../ui/table";
import { Badge } from "../ui/badge";
import { Button } from "../ui/button";
import {
  Edit,
  Trash2,
  Loader2,
  AlertTriangle,
  MapPin,
  Trophy,
} from "lucide-react";
import { motion } from "framer-motion";
import type { Spiel } from "./SpielplanList";

interface SpielplanTableProps {
  spiele: Spiel[];
  isLoading: boolean;
  onEdit: (spiel: Spiel) => void;
  onDelete: (spiel: Spiel) => void;
}

const SpielplanTable = ({
  spiele,
  isLoading,
  onEdit,
  onDelete,
}: SpielplanTableProps) => {
  // Formatiere Datum
  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    return new Intl.DateTimeFormat("de-DE").format(date);
  };

  // Status-Badge-Farbe
  const getStatusBadgeColor = (status: string) => {
    switch (status) {
      case "Geplant":
        return "bg-blue-100 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300";
      case "Live":
        return "bg-green-100 text-green-800 dark:bg-green-900/30 dark:text-green-300";
      case "Beendet":
        return "bg-gray-100 text-gray-800 dark:bg-gray-900/30 dark:text-gray-300";
      case "Verschoben":
        return "bg-yellow-100 text-yellow-800 dark:bg-yellow-900/30 dark:text-yellow-300";
      case "Abgesagt":
        return "bg-red-100 text-red-800 dark:bg-red-900/30 dark:text-red-300";
      default:
        return "bg-gray-100 text-gray-800 dark:bg-gray-900/30 dark:text-gray-300";
    }
  };

  if (isLoading) {
    return (
      <div className="flex flex-col items-center justify-center py-16">
        <div className="relative w-16 h-16 mb-4">
          <div className="absolute inset-0 bg-blue-500 rounded-full blur-md opacity-50 animate-pulse"></div>
          <div className="relative flex items-center justify-center w-full h-full">
            <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
          </div>
        </div>
        <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
          Lade Spieldaten...
        </p>
      </div>
    );
  }

  if (spiele.length === 0) {
    return (
      <div className="flex flex-col items-center justify-center py-16 bg-white/50 dark:bg-gray-800/50 backdrop-blur-md rounded-xl border border-white/20 dark:border-gray-700/50">
        <div className="relative w-16 h-16 mb-4">
          <div className="absolute inset-0 bg-gray-200 dark:bg-gray-700 rounded-full blur-md opacity-50"></div>
          <div className="relative flex items-center justify-center w-full h-full">
            <AlertTriangle className="h-8 w-8 text-gray-400 dark:text-gray-500" />
          </div>
        </div>
        <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
          Keine Spiele gefunden
        </p>
        <p className="text-gray-500 dark:text-gray-400 mt-2">
          Passen Sie Ihre Filterkriterien an oder erstellen Sie ein neues Spiel.
        </p>
      </div>
    );
  }

  return (
    <div className="bg-white/80 dark:bg-gray-800/80 backdrop-blur-md rounded-xl border border-white/50 dark:border-gray-700/50 shadow-xl overflow-hidden">
      <Table>
        <TableHeader className="bg-white/30 dark:bg-gray-800/30 backdrop-blur-md">
          <TableRow className="hover:bg-transparent border-b border-white/20 dark:border-gray-700/50">
            <TableHead className="font-semibold">Datum</TableHead>
            <TableHead className="font-semibold">Mannschaften</TableHead>
            <TableHead className="font-semibold">Ort</TableHead>
            <TableHead className="font-semibold">Liga</TableHead>
            <TableHead className="font-semibold">Status</TableHead>
            <TableHead className="font-semibold">Ergebnis</TableHead>
            <TableHead className="text-right font-semibold">Aktionen</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {spiele.map((spiel, index) => (
            <motion.tr
              key={spiel.id}
              initial={{ opacity: 0, y: 10 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.3, delay: index * 0.05 }}
              className="hover:bg-white/40 dark:hover:bg-gray-800/40 transition-colors border-b border-white/10 dark:border-gray-700/30"
            >
              <TableCell>
                <div className="flex flex-col">
                  <div className="font-medium">{formatDate(spiel.datum)}</div>
                  <div className="text-xs text-gray-500 dark:text-gray-400">
                    {spiel.uhrzeit} Uhr
                  </div>
                </div>
              </TableCell>
              <TableCell>
                <div className="flex items-center gap-2">
                  <div className="font-medium">{spiel.heimMannschaft}</div>
                  <span className="text-gray-500 dark:text-gray-400">vs.</span>
                  <div className="font-medium">{spiel.gastMannschaft}</div>
                </div>
              </TableCell>
              <TableCell>
                <div className="flex items-center gap-2">
                  <MapPin className="h-4 w-4 text-gray-400" />
                  <span>{spiel.ort}</span>
                </div>
              </TableCell>
              <TableCell>
                <div className="flex items-center gap-2">
                  <Trophy className="h-4 w-4 text-gray-400" />
                  <span>{spiel.liga}</span>
                </div>
              </TableCell>
              <TableCell>
                <Badge
                  variant="outline"
                  className={getStatusBadgeColor(spiel.status)}
                >
                  {spiel.status}
                </Badge>
              </TableCell>
              <TableCell>
                {spiel.status === "Beendet" ? (
                  <div className="font-medium">
                    {spiel.heimTore} : {spiel.gastTore}
                  </div>
                ) : (
                  <div className="text-gray-500 dark:text-gray-400">-</div>
                )}
              </TableCell>
              <TableCell className="text-right">
                <div className="flex items-center justify-end gap-2">
                  <Button
                    variant="outline"
                    size="sm"
                    onClick={() => onEdit(spiel)}
                    className="bg-blue-100/50 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300 border-blue-200 dark:border-blue-800/30 hover:bg-blue-200/50 dark:hover:bg-blue-800/40"
                  >
                    <Edit className="h-4 w-4" />
                  </Button>
                  <Button
                    variant="outline"
                    size="sm"
                    onClick={() => onDelete(spiel)}
                    className="bg-red-100/50 text-red-800 dark:bg-red-900/30 dark:text-red-300 border-red-200 dark:border-red-800/30 hover:bg-red-200/50 dark:hover:bg-red-800/40"
                  >
                    <Trash2 className="h-4 w-4" />
                  </Button>
                </div>
              </TableCell>
            </motion.tr>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default SpielplanTable;
