"use client";
import React from "react";
import { useState } from "react";
import { motion } from "framer-motion";
import { Input } from "../ui/input";
import { Button } from "../ui/button";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select";
import { Calendar, Filter, X } from "lucide-react";

interface SpielplanFilterProps {
  mannschaften: { id: number; name: string }[];
  ligen: string[];
  mannschaftFilter: number | null;
  ligaFilter: string | null;
  statusFilter: string | null;
  datumVonFilter: string;
  datumBisFilter: string;
  onMannschaftFilterChange: (value: number | null) => void;
  onLigaFilterChange: (value: string | null) => void;
  onStatusFilterChange: (value: string | null) => void;
  onDatumVonFilterChange: (value: string) => void;
  onDatumBisFilterChange: (value: string) => void;
  onResetFilter: () => void;
}

const SpielplanFilter = ({
  mannschaften,
  ligen,
  mannschaftFilter,
  ligaFilter,
  statusFilter,
  datumVonFilter,
  datumBisFilter,
  onMannschaftFilterChange,
  onLigaFilterChange,
  onStatusFilterChange,
  onDatumVonFilterChange,
  onDatumBisFilterChange,
  onResetFilter,
}: SpielplanFilterProps) => {
  const [isExpanded, setIsExpanded] = useState(false);

  // Prüfe, ob Filter aktiv sind
  const hasActiveFilters =
    mannschaftFilter !== null ||
    ligaFilter !== null ||
    statusFilter !== null ||
    datumVonFilter ||
    datumBisFilter;

  return (
    <div className="space-y-4">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-4">
        <div className="flex items-center gap-2">
          <h3 className="text-lg font-semibold flex items-center">
            <Filter className="h-5 w-5 mr-2 text-blue-600 dark:text-blue-400" />
            Filter
          </h3>
          {hasActiveFilters && (
            <div className="bg-blue-100 dark:bg-blue-900/30 text-blue-800 dark:text-blue-300 text-xs font-medium px-2.5 py-0.5 rounded-full">
              Aktiv
            </div>
          )}
        </div>
        <div className="flex items-center gap-2">
          <Button
            variant="outline"
            size="sm"
            onClick={() => setIsExpanded(!isExpanded)}
            className="border-blue-200 dark:border-blue-800/30 hover:bg-blue-50 dark:hover:bg-blue-900/20"
          >
            {isExpanded ? "Filter einklappen" : "Filter erweitern"}
          </Button>
          {hasActiveFilters && (
            <Button
              variant="outline"
              size="sm"
              onClick={onResetFilter}
              className="border-red-200 dark:border-red-800/30 hover:bg-red-50 dark:hover:bg-red-900/20 text-red-600 dark:text-red-400"
            >
              <X className="h-4 w-4 mr-1" />
              Zurücksetzen
            </Button>
          )}
        </div>
      </div>

      <motion.div
        initial={false}
        animate={{
          height: isExpanded ? "auto" : "0",
          opacity: isExpanded ? 1 : 0,
        }}
        transition={{ duration: 0.3 }}
        className="overflow-hidden"
      >
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 pt-4">
          <div className="space-y-2">
            <label className="text-sm font-medium">Mannschaft</label>
            <Select
              value={mannschaftFilter?.toString() || ""}
              onValueChange={(value) =>
                onMannschaftFilterChange(value ? Number(value) : null)
              }
            >
              <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md">
                <SelectValue placeholder="Alle Mannschaften" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="default">Alle Mannschaften</SelectItem>
                {mannschaften.map((mannschaft) => (
                  <SelectItem
                    key={mannschaft.id}
                    value={mannschaft.id.toString()}
                  >
                    {mannschaft.name}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <label className="text-sm font-medium">Liga</label>
            <Select
              value={ligaFilter || ""}
              onValueChange={(value) => onLigaFilterChange(value || null)}
            >
              <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md">
                <SelectValue placeholder="Alle Ligen" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="default">Alle Ligen</SelectItem>
                {ligen.map((liga) => (
                  <SelectItem key={liga} value={liga}>
                    {liga}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <label className="text-sm font-medium">Status</label>
            <Select
              value={statusFilter || ""}
              onValueChange={(value) => onStatusFilterChange(value || null)}
            >
              <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md">
                <SelectValue placeholder="Alle Status" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="default">Alle Status</SelectItem>
                <SelectItem value="Geplant">Geplant</SelectItem>
                <SelectItem value="Live">Live</SelectItem>
                <SelectItem value="Beendet">Beendet</SelectItem>
                <SelectItem value="Verschoben">Verschoben</SelectItem>
                <SelectItem value="Abgesagt">Abgesagt</SelectItem>
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <label className="text-sm font-medium flex items-center">
              <Calendar className="h-4 w-4 mr-1 text-gray-400" />
              Datum von
            </label>
            <Input
              type="date"
              value={datumVonFilter}
              onChange={(e) => onDatumVonFilterChange(e.target.value)}
              className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md"
            />
          </div>

          <div className="space-y-2">
            <label className="text-sm font-medium flex items-center">
              <Calendar className="h-4 w-4 mr-1 text-gray-400" />
              Datum bis
            </label>
            <Input
              type="date"
              value={datumBisFilter}
              onChange={(e) => onDatumBisFilterChange(e.target.value)}
              className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50 backdrop-blur-md"
            />
          </div>
        </div>
      </motion.div>
    </div>
  );
};

export default SpielplanFilter;
