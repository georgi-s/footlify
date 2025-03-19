"use client";

import React from "react";
import { motion } from "framer-motion";
import { TableCell } from "../ui/table";
import { Shield, Edit, Trash2, ChevronRight, Briefcase } from "lucide-react";
import { Badge } from "../ui/badge";
import {
  getFormationName,
  getLigaName,
  getLigaBadgeColor,
} from "../../utils/mannschaftUtils";
import { Button } from "../ui/button";
import type { MannschaftDTO } from "../../types/MannschaftDTO";

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
  return (
    <motion.tr
      initial={{ opacity: 0, y: 10 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.3, delay: index * 0.05 }}
      className="hover:bg-slate-100/40 dark:hover:bg-slate-800/40 transition-colors border-b border-slate-200/50 dark:border-slate-700/50"
    >
      <TableCell className="font-medium">
        <div className="relative w-10 h-10">
          <div className="absolute inset-0 rounded-full bg-indigo-500/20 blur-sm"></div>
          <div className="relative flex items-center justify-center w-full h-full bg-white dark:bg-slate-800 backdrop-blur-sm rounded-full border border-slate-200 dark:border-slate-700 transition-colors duration-300">
            <span className="font-bold text-indigo-600 dark:text-indigo-400 transition-colors duration-300">
              {mannschaft.id}
            </span>
          </div>
        </div>
      </TableCell>
      <TableCell>
        <div className="flex items-center gap-3">
          <div className="bg-indigo-600 w-9 h-9 rounded-full flex items-center justify-center shadow-md shadow-indigo-500/20">
            <Shield className="h-4 w-4 text-white" />
          </div>
          <span className="font-semibold text-slate-800 dark:text-slate-200 transition-colors duration-300">
            {mannschaft.name}
          </span>
        </div>
      </TableCell>
      <TableCell>
        <div className="flex items-center gap-2">
          <div className="bg-slate-100 dark:bg-slate-700 w-6 h-6 rounded-full flex items-center justify-center transition-colors duration-300">
            <Briefcase className="h-3.5 w-3.5 text-slate-500 dark:text-slate-400 transition-colors duration-300" />
          </div>
          <span className="text-slate-700 dark:text-slate-300 transition-colors duration-300">
            {mannschaft.trainer}
          </span>
        </div>
      </TableCell>
      <TableCell>
        <Badge
          variant="outline"
          className="bg-blue-50 text-blue-700 border-blue-200 dark:bg-blue-900/30 dark:text-blue-300 dark:border-blue-800/50 font-medium px-2.5 py-1 transition-colors duration-300"
        >
          {getFormationName(mannschaft.formation || "")}
        </Badge>
      </TableCell>
      <TableCell>
        <Badge
          variant="outline"
          className="bg-rose-50 text-rose-700 border-rose-200 dark:bg-rose-900/30 dark:text-rose-300 dark:border-rose-800/50 font-medium px-2.5 py-1 transition-colors duration-300"
        >
          {getLigaName(mannschaft.liga || "")}
        </Badge>
      </TableCell>
      <TableCell className="text-right">
        <div className="flex items-center justify-end gap-2">
          <Button
            variant="outline"
            size="sm"
            onClick={() => onEdit(mannschaft)}
            className="border-slate-200 dark:border-slate-700 hover:bg-indigo-50 hover:text-indigo-600 dark:hover:bg-indigo-900/30 dark:hover:text-indigo-400 transition-colors duration-300"
          >
            <Edit className="h-4 w-4" />
          </Button>
          <Button
            variant="outline"
            size="sm"
            onClick={() => onDelete(mannschaft)}
            className="border-slate-200 dark:border-slate-700 hover:bg-rose-50 hover:text-rose-600 dark:hover:bg-rose-900/30 dark:hover:text-rose-400 transition-colors duration-300"
          >
            <Trash2 className="h-4 w-4" />
          </Button>
          <Button
            variant="ghost"
            size="sm"
            onClick={() => onDetails(mannschaft)}
            className="hover:bg-slate-100 dark:hover:bg-slate-800 transition-colors duration-300"
          >
            <ChevronRight className="h-4 w-4" />
          </Button>
        </div>
      </TableCell>
    </motion.tr>
  );
};

export default MannschaftTableRow;
