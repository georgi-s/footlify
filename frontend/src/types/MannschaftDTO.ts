import { Formation, Liga } from './types';

/**
 * DTO für die Kommunikation mit dem Backend bei Mannschaft-Operationen
 */
export interface MannschaftDTO {
  id?: number; // Optional, beim Erstellen nicht vorhanden
  name: string;
  trainer: string;
  formation: Formation;
  liga: Liga;
  feldspielerIds?: number[]; // Optional, IDs der Feldspieler
  auswechselspielerIds?: number[]; // Optional, IDs der Auswechselspieler
}

/**
 * Hilfsfunktion zum Erstellen eines leeren MannschaftDTO mit Standardwerten
 */
export function createDefaultMannschaftDTO(): MannschaftDTO {
  return {
    name: '',
    trainer: '',
    formation: Formation.f442,
    liga: Liga.Bundesliga1
  };
}
