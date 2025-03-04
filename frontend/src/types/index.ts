export interface Spieler {
    id: number;
    name: string;
    alter: number;
    gehalt: number;
    trikotnummer: number;
    position: string;
    mannschaftId?: number;
}

export interface Torwart extends Spieler {
    reaktion: number;
    fangsicherheit: number;
}

export interface Verteidiger extends Spieler {
    zweikampfstaerke: number;
    kopfballstaerke: number;
}

export interface Mittelfeldspieler extends Spieler {
    passgenauigkeit: number;
    ausdauer: number;
}

export interface Stuermer extends Spieler {
    schusskraft: number;
    technik: number;
}

export interface Mannschaft {
    id: number;
    name: string;
    spieler: Spieler[];
    ligaId?: number;
    formationId?: number;
}

export interface Liga {
    id: number;
    name: string;
    land: string;
    mannschaften: Mannschaft[];
}

export interface Formation {
    id: number;
    name: string;
    verteidiger: number;
    mittelfeldspieler: number;
    stuermer: number;
    mannschaftId?: number;
}
