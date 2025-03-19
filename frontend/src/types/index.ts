// src/types/index.ts
export * from "./types";

// API Typen für Frontend-Komponenten

// Backend Formation-Entity
export interface FormationEntity {
  id: number;
  bezeichnung: string;
  verteidigerAnzahl: number;
  mittelfeldspielerAnzahl: number;
  stuermerAnzahl: number;
}

// Backend Liga-Entity
export interface LigaEntity {
  id: number;
  name: string;
  saison?: string;
}

// Spieler von Backend
export interface Spieler {
  id: number;
  name: string;
  position: string;
  bewertung?: number;
  alter: number;
  gehalt: number;
  trikotnummer: number;
  mannschaftId?: number;
}

// Mannschaft von Backend
export interface Mannschaft {
  id: number;
  name: string;
  trainer: string;
  formation: FormationEntity | null;
  liga: LigaEntity | null;
  spieler?: Spieler[];
}
