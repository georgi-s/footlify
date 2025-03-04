"use client";

import { Player } from "../../src/types/team";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Plus, Minus, ArrowUpDown } from "lucide-react";

interface PlayerTableProps {
  players: Player[];
  onPlayerAction: (player: Player) => void;
  actionType: "add" | "remove";
  sortConfig: { key: string; direction: string };
  setSortConfig: (config: { key: string; direction: string }) => void;
}

export default function PlayerTable({
  players,
  onPlayerAction,
  actionType,
  sortConfig,
  setSortConfig,
}: PlayerTableProps) {
  const handleSort = (key: string) => {
    const direction =
      sortConfig.key === key && sortConfig.direction === "asc" ? "desc" : "asc";
    setSortConfig({ key, direction });
  };

  const sortedPlayers = [...players].sort((a, b) => {
    if (!sortConfig.key) return 0;

    const aValue = a[sortConfig.key as keyof Player];
    const bValue = b[sortConfig.key as keyof Player];

    if (typeof aValue === "string" && typeof bValue === "string") {
      return sortConfig.direction === "asc"
        ? aValue.localeCompare(bValue)
        : bValue.localeCompare(aValue);
    }

    if (typeof aValue === "number" && typeof bValue === "number") {
      return sortConfig.direction === "asc" ? aValue - bValue : bValue - aValue;
    }

    return 0;
  });

  const getPositionColor = (position: string) => {
    switch (position.toLowerCase()) {
      case "torwart":
        return "bg-yellow-500";
      case "verteidigung":
        return "bg-blue-500";
      case "mittelfeld":
        return "bg-green-500";
      case "sturm":
        return "bg-red-500";
      default:
        return "bg-gray-500";
    }
  };

  return (
    <div className="rounded-xl overflow-hidden border border-white/20 dark:border-gray-800/50">
      <Table>
        <TableHeader className="bg-gray-50/50 dark:bg-gray-900/50">
          <TableRow>
            <TableHead className="w-[40px] text-center">#</TableHead>
            <TableHead>
              <Button
                variant="ghost"
                onClick={() => handleSort("nachname")}
                className="hover:bg-transparent"
              >
                Name
                <ArrowUpDown className="ml-2 h-4 w-4" />
              </Button>
            </TableHead>
            <TableHead>Position</TableHead>
            <TableHead className="text-right">
              <Button
                variant="ghost"
                onClick={() => handleSort("bewertung")}
                className="hover:bg-transparent"
              >
                Rating
                <ArrowUpDown className="ml-2 h-4 w-4" />
              </Button>
            </TableHead>
            <TableHead className="text-right">
              <Button
                variant="ghost"
                onClick={() => handleSort("alter")}
                className="hover:bg-transparent"
              >
                Age
                <ArrowUpDown className="ml-2 h-4 w-4" />
              </Button>
            </TableHead>
            <TableHead className="text-right">Action</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {sortedPlayers.map((player) => (
            <TableRow
              key={player.id}
              className="hover:bg-gray-50/50 dark:hover:bg-gray-800/50 transition-colors"
            >
              <TableCell className="text-center font-medium">
                {player.trikotnummer}
              </TableCell>
              <TableCell>
                {player.vorname} {player.nachname}
              </TableCell>
              <TableCell>
                <Badge
                  variant="secondary"
                  className={`${getPositionColor(
                    player.position
                  )} text-white hover:${getPositionColor(player.position)}`}
                >
                  {player.position}
                </Badge>
              </TableCell>
              <TableCell className="text-right">{player.bewertung}</TableCell>
              <TableCell className="text-right">{player.alter}</TableCell>
              <TableCell className="text-right">
                <Button
                  size="sm"
                  variant={actionType === "add" ? "default" : "destructive"}
                  onClick={() => onPlayerAction(player)}
                >
                  {actionType === "add" ? (
                    <Plus className="h-4 w-4" />
                  ) : (
                    <Minus className="h-4 w-4" />
                  )}
                </Button>
              </TableCell>
            </TableRow>
          ))}
          {players.length === 0 && (
            <TableRow>
              <TableCell colSpan={6} className="text-center py-8">
                <p className="text-gray-500 dark:text-gray-400">
                  No players available
                </p>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </div>
  );
}
