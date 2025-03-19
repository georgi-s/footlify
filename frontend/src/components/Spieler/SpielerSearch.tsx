"use client";

import React from "react";
import { Input } from "../ui/input";
import { Search } from "lucide-react";

interface SpielerSearchProps {
  searchTerm: string;
  onSearchChange: (value: string) => void;
}

const SpielerSearch = ({ searchTerm, onSearchChange }: SpielerSearchProps) => {
  return (
    <div className="relative w-full max-w-md">
      <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
        <Search className="h-4 w-4 text-gray-400" />
      </div>
      <Input
        type="text"
        placeholder="Spieler nach Vor- oder Nachname suchen..."
        value={searchTerm}
        onChange={(e) => onSearchChange(e.target.value)}
        className="pl-10 border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md focus:ring-2 focus:ring-blue-500/20"
      />
    </div>
  );
};

export default SpielerSearch;
