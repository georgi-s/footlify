-- Basisschema für die Migration der Sportverein-Datenbank

-- Liga-Tabelle
CREATE TABLE IF NOT EXISTS liga (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    saison VARCHAR(10) NOT NULL
);

-- Formation-Tabelle (falls benötigt)
CREATE TABLE IF NOT EXISTS formation (
    id SERIAL PRIMARY KEY,
    bezeichnung VARCHAR(50) NOT NULL,
    verteidiger_anzahl INTEGER NOT NULL,
    mittelfeldspieler_anzahl INTEGER NOT NULL,
    stuermer_anzahl INTEGER NOT NULL
);

-- Mannschaft-Tabelle
CREATE TABLE IF NOT EXISTS mannschaft (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    trainer VARCHAR(255),
    formation_id INTEGER REFERENCES formation(id),
    liga_id INTEGER REFERENCES liga(id)
);

-- Spieler-Tabelle (Basis für Positionsspieler)
CREATE TABLE IF NOT EXISTS spieler (
    id SERIAL PRIMARY KEY,
    vorname VARCHAR(255) NOT NULL,
    nachname VARCHAR(255) NOT NULL,
    geburtsdatum DATE,
    position VARCHAR(50) NOT NULL,
    mannschaft_id INTEGER REFERENCES mannschaft(id),
    ist_feldspieler BOOLEAN DEFAULT true,
    trikotnummer INTEGER
);

-- Spiel-Tabelle
CREATE TABLE IF NOT EXISTS spiel (
    id SERIAL PRIMARY KEY,
    heim_mannschaft_id INTEGER REFERENCES mannschaft(id) NOT NULL,
    gast_mannschaft_id INTEGER REFERENCES mannschaft(id) NOT NULL,
    datum DATE NOT NULL,
    uhrzeit TIME,
    ort VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    heim_tore INTEGER,
    gast_tore INTEGER,
    liga_id INTEGER REFERENCES liga(id)
);

-- Turnier-Tabelle
CREATE TABLE IF NOT EXISTS turnier (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ort VARCHAR(255),
    datum DATE,
    preisgeld DECIMAL(10, 2)
);

-- Turnier-Mannschaft Zuordnung (Many-to-Many)
CREATE TABLE IF NOT EXISTS turnier_mannschaft (
    turnier_id INTEGER REFERENCES turnier(id),
    mannschaft_id INTEGER REFERENCES mannschaft(id),
    PRIMARY KEY (turnier_id, mannschaft_id)
);

-- Indizes für bessere Performance
CREATE INDEX IF NOT EXISTS idx_spieler_mannschaft ON spieler(mannschaft_id);
CREATE INDEX IF NOT EXISTS idx_mannschaft_liga ON mannschaft(liga_id);
CREATE INDEX IF NOT EXISTS idx_spiel_liga ON spiel(liga_id);
CREATE INDEX IF NOT EXISTS idx_spiel_heim_mannschaft ON spiel(heim_mannschaft_id);
CREATE INDEX IF NOT EXISTS idx_spiel_gast_mannschaft ON spiel(gast_mannschaft_id);
