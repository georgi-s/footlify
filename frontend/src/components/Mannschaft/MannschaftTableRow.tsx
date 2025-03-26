"use client";

import React from "react";
import { TableCell } from "../../components/ui/table";
import { Button } from "../../components/ui/button";
import { Edit, Trash2, Eye } from "lucide-react";
import { motion } from "framer-motion";
import type { MannschaftDTO } from "../../types/MannschaftDTO";
import { Badge } from "../../components/ui/badge";

type MannschaftTableRowProps = {
  mannschaft: MannschaftDTO;
  onEdit: (mannschaft: MannschaftDTO) => void;
  onDelete: (mannschaft: MannschaftDTO) => void;
  onDetails: (mannschaft: MannschaftDTO) => void;
  index: number;
};

const MannschaftTableRow: React.FC<MannschaftTableRowProps> = ({
  mannschaft,
  onEdit,
  onDelete,
  onDetails,
  index,
}) => {
  // Detaillierte Debug-Ausgabe, um zu sehen, was tatsächlich in den Daten enthalten ist
  console.log("Mannschaft vollständig:", JSON.stringify(mannschaft, null, 2));
  console.log("Formation:", mannschaft.formation);
  console.log("Liga:", mannschaft.liga);

  // Hilfsfunktion, um Formation sicher anzuzeigen
  const getFormationDisplay = () => {
    // Wenn formation undefined oder null ist
    if (!mannschaft.formation) {
      console.log("Formation ist null oder undefined");
      return "Nicht festgelegt";
    }

    // Typprüfung mit any, um TypeScript-Fehler zu vermeiden
    const formationObj = mannschaft.formation as any;
    console.log("Formation als any:", formationObj);

    // Wenn formation ein Objekt mit bezeichnung ist
    if (typeof formationObj === "object" && formationObj !== null) {
      console.log("Formation ist ein Objekt");
      if (formationObj.bezeichnung) {
        console.log("Formation hat bezeichnung:", formationObj.bezeichnung);
        return formationObj.bezeichnung;
      }
    }

    // Wenn formation ein String ist
    if (typeof formationObj === "string") {
      console.log("Formation ist ein String:", formationObj);
      // Entferne das "f" am Anfang und formatiere (z.B. "442" -> "4-4-2")
      if (formationObj.startsWith("f") && /^f\d+$/.test(formationObj)) {
        const formationDigits = formationObj.substring(1);
        return formationDigits.split("").join("-");
      }
      return formationObj;
    }

    // Fallback
    console.log("Formation Fallback verwendet");
    return "Nicht festgelegt";
  };

  const getLigaDisplay = () => {
    // Wenn liga undefined oder null ist
    if (!mannschaft.liga) {
      console.log("Liga ist null oder undefined");
      return "Nicht festgelegt";
    }

    // Typprüfung mit any, um TypeScript-Fehler zu vermeiden
    const ligaObj = mannschaft.liga as any;
    console.log("Liga als any:", ligaObj);

    // Wenn liga ein Objekt mit name ist
    if (typeof ligaObj === "object" && ligaObj !== null) {
      console.log("Liga ist ein Objekt");
      if (ligaObj.name) {
        console.log("Liga hat name:", ligaObj.name);
        return ligaObj.name;
      }
    }

    // Wenn liga ein String ist
    if (typeof ligaObj === "string") {
      console.log("Liga ist ein String:", ligaObj);
      // Formatiere den Enum-Wert für die Anzeige
      if (ligaObj === "Bundesliga1") return "1. Bundesliga";
      if (ligaObj === "Bundesliga2") return "2. Bundesliga";
      if (ligaObj === "Bundesliga3") return "3. Liga";
      return ligaObj;
    }

    // Fallback
    console.log("Liga Fallback verwendet");
    return "Nicht festgelegt";
  };

  // Direkte Anzeige der Werte für Debugging
  const formationDisplay = getFormationDisplay();
  const ligaDisplay = getLigaDisplay();

  console.log("Formation Display:", formationDisplay);
  console.log("Liga Display:", ligaDisplay);

  return (
    <motion.tr
      initial={{ opacity: 0, y: 10 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.3, delay: index * 0.05 }}
      className="hover:bg-slate-50/50 dark:hover:bg-slate-800/50 transition-colors"
    >
      <TableCell className="font-medium">
        <Badge
          variant="outline"
          className="bg-blue-100/50 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300 border-blue-200 dark:border-blue-800/30"
        >
          {mannschaft.id}
        </Badge>
      </TableCell>
      <TableCell>{mannschaft.name}</TableCell>
      <TableCell>{mannschaft.trainer || "Kein Trainer"}</TableCell>
      <TableCell>
        <Badge
          variant="outline"
          className="bg-green-100/50 text-green-800 dark:bg-green-900/30 dark:text-green-300 border-green-200 dark:border-green-800/30"
        >
          {formationDisplay}
        </Badge>
      </TableCell>
      <TableCell>
        <Badge
          variant="outline"
          className="bg-purple-100/50 text-purple-800 dark:bg-purple-900/30 dark:text-purple-300 border-purple-200 dark:border-purple-800/30"
        >
          {ligaDisplay}
        </Badge>
      </TableCell>
      <TableCell className="text-right">
        <div className="flex items-center justify-end gap-2">
          <Button
            variant="outline"
            size="sm"
            onClick={() => onDetails(mannschaft)}
            className="bg-indigo-100/50 text-indigo-800 dark:bg-indigo-900/30 dark:text-indigo-300 border-indigo-200 dark:border-indigo-800/30 hover:bg-indigo-200/50 dark:hover:bg-indigo-800/40"
          >
            <Eye className="h-4 w-4" />
          </Button>
          <Button
            variant="outline"
            size="sm"
            onClick={() => onEdit(mannschaft)}
            className="bg-blue-100/50 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300 border-blue-200 dark:border-blue-800/30 hover:bg-blue-200/50 dark:hover:bg-blue-800/40"
          >
            <Edit className="h-4 w-4" />
          </Button>
          <Button
            variant="outline"
            size="sm"
            onClick={() => onDelete(mannschaft)}
            className="bg-red-100/50 text-red-800 dark:bg-red-900/30 dark:text-red-300 border-red-200 dark:border-red-800/30 hover:bg-red-200/50 dark:hover:bg-red-800/40"
          >
            <Trash2 className="h-4 w-4" />
          </Button>
        </div>
      </TableCell>
    </motion.tr>
  );
};

export default MannschaftTableRow;
