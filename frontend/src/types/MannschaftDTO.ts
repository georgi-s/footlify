import { Formation, Liga } from "./types";

/**
 * DTO für die Kommunikation mit dem Backend bei Mannschaft-Operationen
 */
export interface MannschaftDTO {
  id?: number;
  name: string;
  trainer: string;
  formation:
    | Formation
    | { id: number; bezeichnung: string /* weitere Eigenschaften */ };
  liga: Liga | { id: number; name: string /* weitere Eigenschaften */ };
  feldspielerIds?: number[];
  auswechselspielerIds?: number[];
}

/**
 * Hilfsfunktion zum Erstellen eines leeren MannschaftDTO mit Standardwerten
 */
export function createDefaultMannschaftDTO(): MannschaftDTO {
  return {
    name: "",
    trainer: "",
    formation: Formation.f442,
    liga: Liga.Bundesliga1,
  };
}
