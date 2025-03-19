/**
 * Definiert die möglichen Werte für eine Spielformation
 * Entspricht dem Formation-Enum im Backend
 */
export enum Formation {
  f442 = 'f442',
  f532 = 'f532',
  f433 = 'f433',
  f343 = 'f343'
}

/**
 * Definiert die möglichen Ligen
 * Entspricht dem Liga-Enum im Backend
 */
export enum Liga {
  Bundesliga1 = 'Bundesliga1',
  Bundesliga2 = 'Bundesliga2',
  Bundesliga3 = 'Bundesliga3',
  RegionalligaWest = 'RegionalligaWest',
  OberligaWestfalen = 'OberligaWestfalen'
}

/**
 * Definiert die Status-Werte für ein Spiel
 * Entspricht dem SpielStatus-Enum im Backend
 */
export enum SpielStatus {
  GEPLANT = 'GEPLANT',
  LIVE = 'LIVE',
  BEENDET = 'BEENDET',
  VERSCHOBEN = 'VERSCHOBEN',
  ABGESAGT = 'ABGESAGT'
}
