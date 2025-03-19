/**
 * Allgemeines DTO für die Kommunikation mit dem Backend
 * Wird sowohl für die Erstellung als auch für die Aktualisierung verwendet
 */
export interface SpielerDTO {
  playerId?: number; // Optional, beim Erstellen nicht vorhanden
  nachname: string;
  vorname: string;
  geburtsdatum: string; // ISO-String, z.B. "2025-03-11T00:00:00.000Z"
  gespielteSpiele: number;
  gesperrt: boolean;
  vereinsbeitritt: string; // ISO-String
  roteKarten: number;
  gelbeKarten: number;
  spielerType: string; // "Mittelfeldspieler", "Stürmer", "Torwart", "Verteidiger"
  mannschaftId?: number; // Optional, für die Zuordnung zu einer Mannschaft
  
  // Felder für Mittelfeldspieler
  anzahlVorlagen?: number;
  tore?: number;
  passquote?: number;
  
  // Felder für Stürmer
  schuesse?: number;
  torschusspraezision?: number;
  
  // Felder für Torwart
  gehalteneSchuesse?: number;
  gegenTore?: number;
  gehalteneElfmeter?: number;
  
  // Felder für Verteidiger
  gegraetschteZweikampfGewonnenProzent?: number;
  kopfballDuellGewonnenProzent?: number;
  abgefangenePassQuote?: number;
}

/**
 * Hilfsfunktion zum Erstellen eines SpielerDTOs mit den richtigen Standardwerten
 * basierend auf dem Spielertyp
 */
export function createDefaultSpielerDTO(spielerType: string): SpielerDTO {
  const basePlayer: SpielerDTO = {
    nachname: '',
    vorname: '',
    geburtsdatum: new Date().toISOString(),
    gespielteSpiele: 0,
    gesperrt: false,
    vereinsbeitritt: new Date().toISOString(),
    roteKarten: 0,
    gelbeKarten: 0,
    spielerType
  };

  switch (spielerType) {
    case 'Mittelfeldspieler':
      return {
        ...basePlayer,
        anzahlVorlagen: 0,
        tore: 0,
        passquote: 0
      };
    case 'Stürmer':
      return {
        ...basePlayer,
        tore: 0,
        schuesse: 0,
        torschusspraezision: 0
      };
    case 'Torwart':
      return {
        ...basePlayer,
        gehalteneSchuesse: 0,
        gegenTore: 0,
        gehalteneElfmeter: 0
      };
    case 'Verteidiger':
      return {
        ...basePlayer,
        gegraetschteZweikampfGewonnenProzent: 0,
        kopfballDuellGewonnenProzent: 0,
        abgefangenePassQuote: 0
      };
    default:
      return basePlayer;
  }
}
