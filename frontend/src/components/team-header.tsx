"use client";

import { Team } from "../../src/types/team";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Shield } from "lucide-react";

interface TeamHeaderProps {
  teams: Team[];
  selectedTeam: Team | null;
  onTeamChange: (teamId: number) => void;
}

export default function TeamHeader({
  teams,
  selectedTeam,
  onTeamChange,
}: TeamHeaderProps) {
  return (
    <div className="bg-gradient-to-br from-blue-500/10 to-indigo-500/10 dark:from-blue-500/5 dark:to-indigo-500/5 p-6 md:p-8 border-b border-white/20 dark:border-gray-800/50">
      <div className="flex items-center gap-4">
        <div className="bg-gradient-to-br from-blue-500 to-indigo-600 p-3 rounded-xl shadow-lg">
          <Shield className="h-6 w-6 text-white" />
        </div>
        <div className="flex-1">
          <h2 className="text-2xl font-bold text-gray-900 dark:text-gray-100">
            Team Management
          </h2>
          <p className="text-gray-500 dark:text-gray-400">
            Select a team to manage players and details
          </p>
        </div>
        <Select
          value={selectedTeam?.id.toString()}
          onValueChange={(value) => onTeamChange(parseInt(value))}
        >
          <SelectTrigger className="w-[240px] bg-white/80 dark:bg-gray-800/80 backdrop-blur-md border-white/50 dark:border-gray-700/50">
            <SelectValue placeholder="Select a team" />
          </SelectTrigger>
          <SelectContent>
            {teams.map((team) => (
              <SelectItem key={team.id} value={team.id.toString()}>
                {team.name}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>
    </div>
  );
}
