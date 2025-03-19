/**
 * Basisinterface für Spieler
 * Entspricht dem abstrakten Spieler im Backend
 */
export interface Spieler {
  playerId: number;
  nachname: string;
  vorname: string;
  geburtsdatum: string; // ISO-String
  gespielteSpiele: number;
  gesperrt: boolean;
  vereinsbeitritt: string; // ISO-String
  roteKarten: number;
  gelbeKarten: number;
  spielerType: string; // Diskriminatorfeld für Spielertyp
  mannschaftId?: number; // Optional für die Zuordnung
}

/**
 * Interface für Mittelfeldspieler
 */
export interface Mittelfeldspieler extends Spieler {
  spielerType: 'Mittelfeldspieler';
  anzahlVorlagen: number;
  tore: number;
  passquote: number;
}

/**
 * Interface für Stürmer
 */
export interface Stuermer extends Spieler {
  spielerType: 'Stürmer';
  tore: number;
  schuesse: number;
  torschusspraezision: number;
}

/**
 * Interface für Torwart
 */
export interface Torwart extends Spieler {
  spielerType: 'Torwart';
  gehalteneSchuesse: number;
  gegenTore: number;
  gehalteneElfmeter: number;
}

/**
 * Interface für Verteidiger
 */
export interface Verteidiger extends Spieler {
  spielerType: 'Verteidiger';
  gegraetschteZweikampfGewonnenProzent: number;
  kopfballDuellGewonnenProzent: number;
  abgefangenePassQuote: number;
}

/**
 * Typ für alle möglichen Spielertypen
 */
export type SpielerTyp = Mittelfeldspieler | Stuermer | Torwart | Verteidiger;
