import { Spieler } from './Spieler';
import { Formation } from './types';
import { Liga } from './types';

/**
 * Interface für eine Mannschaft
 * Entspricht dem Mannschaft-Model im Backend
 */
export interface Mannschaft {
  id: number;
  name: string;
  trainer: string;
  feldspieler: Spieler[];
  auswechselspieler: Spieler[];
  formation: Formation;
  liga: Liga;
}
