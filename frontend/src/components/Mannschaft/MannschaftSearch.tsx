"use client";

import React from "react";
import { Input } from "../ui/input";
import { Search } from "lucide-react";
import { motion } from "framer-motion";

type MannschaftSearchProps = {
  searchTerm: string;
  setSearchTerm: (term: string) => void;
};

const MannschaftSearch: React.FC<MannschaftSearchProps> = ({
  searchTerm,
  setSearchTerm,
}) => {
  return (
    <motion.div
      initial={{ opacity: 0, x: -20 }}
      animate={{ opacity: 1, x: 0 }}
      transition={{ duration: 0.5 }}
      className="relative w-full max-w-md"
    >
      <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
        <Search className="h-4 w-4 text-slate-400" />
      </div>
      <Input
        type="text"
        placeholder="Mannschaft oder Trainer suchen..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="pl-10 border-slate-200 dark:border-slate-700 bg-white/70 dark:bg-slate-800/50 backdrop-blur-md focus-visible:ring-2 focus-visible:ring-indigo-500/30 dark:focus-visible:ring-indigo-400/30 transition-all duration-200 shadow-sm text-slate-800 dark:text-slate-200 placeholder:text-slate-400 dark:placeholder:text-slate-500"
      />
    </motion.div>
  );
};

export default MannschaftSearch;
