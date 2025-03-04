import React, { useState, useEffect } from "react";
import { mannschaftApi, spielerApi } from "../services/api";
import type { ApiResponseData } from "../services/api";
import { Spieler as ApiSpieler, Mannschaft as ApiMannschaft } from "../types";

import { Button } from "./ui/button";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "./ui/select";
import { Input } from "./ui/input";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "./ui/table";
import { toast } from "sonner";

import { Card, CardContent } from "./ui/card";

// Frontend-spezifische Typen als Type Aliases
type FrontendSpieler = {
  id: number;
  vorname: string;
  nachname: string;
  position: string;
  bewertung: number;
  alter: number;
  gehalt: number;
  trikotnummer: number;
  mannschaftId?: number;
};

type FrontendMannschaft = {
  id: number;
  name: string;
  trainer: string;
  formation: string;
  liga: string;
  spieler: FrontendSpieler[];
};

// Konvertierungsfunktionen
const convertApiSpielerToSpieler = (
  apiSpieler: ApiSpieler
): FrontendSpieler => ({
  id: apiSpieler.id,
  vorname: apiSpieler.name.split(" ")[0],
  nachname: apiSpieler.name.split(" ")[1] || "",
  position: apiSpieler.position,
  bewertung: 0,
  alter: apiSpieler.alter,
  gehalt: apiSpieler.gehalt,
  trikotnummer: apiSpieler.trikotnummer,
  mannschaftId: apiSpieler.mannschaftId,
});

const convertApiMannschaftToMannschaft = (
  apiMannschaft: ApiMannschaft
): FrontendMannschaft => ({
  id: apiMannschaft.id,
  name: apiMannschaft.name,
  trainer: "",
  formation: "4-4-2",
  liga: "BUNDESLIGA",
  spieler: (apiMannschaft.spieler || []).map(convertApiSpielerToSpieler),
});

const ClubPanel: React.FC = () => {
  const [selectedClub, setSelectedClub] = useState<FrontendMannschaft | null>(
    null
  );
  const [clubs, setClubs] = useState<FrontendMannschaft[]>([]);
  const [availablePlayers, setAvailablePlayers] = useState<FrontendSpieler[]>(
    []
  );
  const [teamPlayers, setTeamPlayers] = useState<FrontendSpieler[]>([]);

  // Type Guard Funktionen
  const isMannschaft = (obj: any): obj is FrontendMannschaft => {
    return (
      obj &&
      typeof obj.id === "number" &&
      typeof obj.name === "string" &&
      typeof obj.trainer === "string" &&
      typeof obj.formation === "string" &&
      typeof obj.liga === "string" &&
      Array.isArray(obj.spieler)
    );
  };

  const isSpieler = (obj: any): obj is FrontendSpieler => {
    return (
      obj &&
      typeof obj.id === "number" &&
      typeof obj.vorname === "string" &&
      typeof obj.nachname === "string" &&
      typeof obj.position === "string" &&
      typeof obj.bewertung === "number"
    );
  };

  useEffect(() => {
    // Lade initial Daten
    const fetchInitialData = async () => {
      try {
        const responses = await Promise.all([
          mannschaftApi.getAll(),
          spielerApi.getAll(),
        ]);

        const [clubsResponse, playersResponse] = responses as [
          ApiResponseData<ApiMannschaft[]>,
          ApiResponseData<ApiSpieler[]>
        ];

        const convertedClubs = clubsResponse.data.map(
          convertApiMannschaftToMannschaft
        );
        const convertedPlayers = playersResponse.data.map(
          convertApiSpielerToSpieler
        );

        setClubs(convertedClubs);
        setAvailablePlayers(convertedPlayers);
      } catch (error) {
        toast.error("Fehler beim Laden der Daten");
      }
    };

    fetchInitialData();
  }, []);

  const refreshData = async () => {
    try {
      const responses = await Promise.all([
        mannschaftApi.getAll(),
        spielerApi.getAll(),
      ]);

      const [clubsResponse, playersResponse] = responses as [
        ApiResponseData<ApiMannschaft[]>,
        ApiResponseData<ApiSpieler[]>
      ];

      const convertedClubs = clubsResponse.data.map((club) => {
        const mannschaft = convertApiMannschaftToMannschaft(club);
        if (!isMannschaft(mannschaft)) {
          throw new Error(`Ungültige Mannschaftsdaten für ID ${club.id}`);
        }
        return mannschaft;
      });
      setClubs(convertedClubs);

      const convertedPlayers = playersResponse.data.map((player) => {
        const spieler = convertApiSpielerToSpieler(player);
        if (!isSpieler(spieler)) {
          throw new Error(`Ungültige Spielerdaten für ID ${player.id}`);
        }
        return spieler;
      });
      setAvailablePlayers(convertedPlayers);
    } catch (error) {
      toast.error("Fehler beim Laden der Daten");
    }
  };

  const handleClubChange = async (clubId: string) => {
    try {
      const response = await mannschaftApi.getById(parseInt(clubId));
      const apiResponse = response as ApiResponseData<ApiMannschaft>;
      const mannschaft = convertApiMannschaftToMannschaft(apiResponse.data);

      if (!isMannschaft(mannschaft)) {
        throw new Error(
          `Ungültige Mannschaftsdaten für ID ${apiResponse.data.id}`
        );
      }

      const validSpieler = mannschaft.spieler.map((spieler) => {
        if (!isSpieler(spieler)) {
          throw new Error(
            `Ungültige Spielerdaten in Mannschaft ${mannschaft.id}`
          );
        }
        return spieler;
      });

      setSelectedClub(mannschaft);
      setTeamPlayers(validSpieler);
    } catch (error) {
      toast.error("Fehler beim Laden der Mannschaftsdetails");
    }
  };

  const handlePlayerTransfer = async (playerId: number, toTeam: boolean) => {
    try {
      await spielerApi.transfer(
        playerId,
        toTeam ? selectedClub?.id : undefined
      );

      // Aktualisiere die Spielerlisten
      refreshData();
      if (selectedClub) {
        handleClubChange(selectedClub.id.toString());
      }

      toast.success("Transfer erfolgreich");
    } catch (error) {
      toast.error("Fehler beim Transfer");
    }
  };

  const handleFormationCheck = async () => {
    if (!selectedClub) return;

    try {
      const response = await mannschaftApi.checkFormation(selectedClub.id);
      if (response.data.valid) {
        toast.success("Formation gültig", {
          description: response.data.message,
        });
      } else {
        toast.warning("Formation ungültig", {
          description: response.data.message,
        });
      }
    } catch (error) {
      toast.error("Fehler bei der Formationsprüfung");
    }
  };

  const PlayerTable: React.FC<{
    players: FrontendSpieler[];
    onTransfer: (playerId: number) => void;
    buttonText: string;
  }> = ({ players, onTransfer, buttonText }) => (
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>ID</TableHead>
          <TableHead>Vorname</TableHead>
          <TableHead>Nachname</TableHead>
          <TableHead>Position</TableHead>
          <TableHead>Bewertung</TableHead>
          <TableHead>Aktion</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {players.map((player) => (
          <TableRow key={player.id}>
            <TableCell>{player.id}</TableCell>
            <TableCell>{player.vorname}</TableCell>
            <TableCell>{player.nachname}</TableCell>
            <TableCell>{player.position}</TableCell>
            <TableCell>{player.bewertung}</TableCell>
            <TableCell>
              <Button
                size="sm"
                variant="outline"
                onClick={() => onTransfer(player.id)}
              >
                {buttonText}
              </Button>
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );

  return (
    <div className="container max-w-7xl mx-auto p-6">
      <div className="space-y-6">
        <Select onValueChange={(value) => handleClubChange(value)}>
          <SelectTrigger>
            <SelectValue placeholder="Mannschaft auswählen" />
          </SelectTrigger>
          <SelectContent>
            {clubs.map((club) => (
              <SelectItem key={club.id} value={club.id.toString()}>
                {club.name}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>

        {selectedClub && (
          <Card>
            <CardContent className="pt-6">
              <div className="flex gap-4 mb-6">
                <Input
                  placeholder="Trainer"
                  value={selectedClub.trainer}
                  onChange={(e) =>
                    setSelectedClub({
                      ...selectedClub,
                      trainer: e.target.value,
                    })
                  }
                />
                <Select
                  value={selectedClub.formation}
                  onValueChange={(value) =>
                    setSelectedClub({
                      ...selectedClub,
                      formation: value,
                    })
                  }
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Formation" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="4-4-2">4-4-2</SelectItem>
                    <SelectItem value="4-3-3">4-3-3</SelectItem>
                    <SelectItem value="3-5-2">3-5-2</SelectItem>
                  </SelectContent>
                </Select>
                <Select
                  value={selectedClub.liga}
                  onValueChange={(value) =>
                    setSelectedClub({ ...selectedClub, liga: value })
                  }
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Liga" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="BUNDESLIGA">Bundesliga</SelectItem>
                    <SelectItem value="ZWEITE_BUNDESLIGA">
                      2. Bundesliga
                    </SelectItem>
                    <SelectItem value="DRITTE_LIGA">3. Liga</SelectItem>
                  </SelectContent>
                </Select>
                <Button variant="default" onClick={handleFormationCheck}>
                  Aufstellung prüfen
                </Button>
              </div>

              <div className="space-y-6">
                <PlayerTable
                  players={availablePlayers}
                  onTransfer={(id) => handlePlayerTransfer(id, true)}
                  buttonText="→ Zum Team"
                />

                <PlayerTable
                  players={teamPlayers}
                  onTransfer={(id) => handlePlayerTransfer(id, false)}
                  buttonText="← Aus Team"
                />
              </div>
            </CardContent>
          </Card>
        )}
      </div>
    </div>
  );
};

export default ClubPanel;
