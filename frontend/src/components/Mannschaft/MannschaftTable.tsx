"use client";

import React from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHeader,
  TableRow,
} from "../ui/table";
import MannschaftTableRow from "./MannschaftTableRow";
import type { MannschaftDTO } from "../../types/MannschaftDTO";
import { motion } from "framer-motion";

type MannschaftTableProps = {
  mannschaften: MannschaftDTO[];
  onEdit: (mannschaft: MannschaftDTO) => void;
  onDelete: (mannschaft: MannschaftDTO) => void;
  onDetails: (mannschaft: MannschaftDTO) => void;
};

const MannschaftTable: React.FC<MannschaftTableProps> = ({
  mannschaften,
  onEdit,
  onDelete,
  onDetails,
}) => {
  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
      className="bg-white/90 dark:bg-slate-900/90 backdrop-blur-md rounded-xl border border-slate-200/50 dark:border-slate-700/50 shadow-xl overflow-hidden transition-colors duration-300"
    >
      <div className="overflow-x-auto">
        <Table>
          <TableHeader className="bg-slate-50/80 dark:bg-slate-800/80 backdrop-blur-md transition-colors duration-300">
            <TableRow className="hover:bg-transparent border-b border-slate-200/50 dark:border-slate-700/50 transition-colors duration-300">
              <TableCell className="w-[50px] font-bold text-slate-700 dark:text-slate-300 transition-colors duration-300">
                ID
              </TableCell>
              <TableCell className="font-bold text-slate-700 dark:text-slate-300 transition-colors duration-300">
                Name
              </TableCell>
              <TableCell className="font-bold text-slate-700 dark:text-slate-300 transition-colors duration-300">
                Trainer
              </TableCell>
              <TableCell className="font-bold text-slate-700 dark:text-slate-300 transition-colors duration-300">
                Formation
              </TableCell>
              <TableCell className="font-bold text-slate-700 dark:text-slate-300 transition-colors duration-300">
                Liga
              </TableCell>
              <TableCell className="text-right font-bold text-slate-700 dark:text-slate-300 transition-colors duration-300">
                Aktionen
              </TableCell>
            </TableRow>
          </TableHeader>
          <TableBody>
            {mannschaften.map((mannschaft, index) => (
              <MannschaftTableRow
                key={mannschaft.id ?? index}
                mannschaft={mannschaft}
                onEdit={onEdit}
                onDelete={onDelete}
                onDetails={onDetails}
                index={index}
              />
            ))}
          </TableBody>
        </Table>
      </div>
    </motion.div>
  );
};

export default MannschaftTable;
