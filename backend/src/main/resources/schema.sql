-- Drop tables if they exist
DROP TABLE IF EXISTS stuermer CASCADE;
DROP TABLE IF EXISTS mittelfeldspieler CASCADE;
DROP TABLE IF EXISTS torwart CASCADE;
DROP TABLE IF EXISTS spieler CASCADE;
DROP TABLE IF EXISTS mannschaft CASCADE;
DROP TABLE IF EXISTS formation CASCADE;
DROP TABLE IF EXISTS liga CASCADE;

-- Create Liga table
CREATE TABLE liga (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    land VARCHAR(255) NOT NULL
);

-- Create Formation table
CREATE TABLE formation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    verteidiger_anzahl INTEGER NOT NULL,
    mittelfeldspieler_anzahl INTEGER NOT NULL,
    stuermer_anzahl INTEGER NOT NULL
);

-- Create Mannschaft table
CREATE TABLE mannschaft (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    liga_id INTEGER REFERENCES liga(id)
);

-- Create Spieler table
CREATE TABLE spieler (
    id SERIAL PRIMARY KEY,
    vorname VARCHAR(255) NOT NULL,
    nachname VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    geburtsdatum DATE NOT NULL,
    trikotnummer INTEGER NOT NULL,
    gehalt DECIMAL(10,2) NOT NULL,
    mannschaft_id INTEGER REFERENCES mannschaft(id),
    gespielte_spiele INTEGER DEFAULT 0,
    gelbe_karten INTEGER DEFAULT 0,
    rote_karten INTEGER DEFAULT 0,
    gesperrt BOOLEAN DEFAULT false,
    vereinsbeitritt DATE NOT NULL
);

-- Create Torwart table
CREATE TABLE torwart (
    id INTEGER PRIMARY KEY REFERENCES spieler(id),
    spiele_ohne_gegentor INTEGER NOT NULL,
    gegentore INTEGER NOT NULL,
    haltequote DOUBLE PRECISION NOT NULL
);

-- Create Mittelfeldspieler table
CREATE TABLE mittelfeldspieler (
    id INTEGER PRIMARY KEY REFERENCES spieler(id),
    passgenauigkeit INTEGER NOT NULL,
    ausdauer INTEGER NOT NULL,
    anzahl_vorlagen INTEGER NOT NULL
);

-- Create Stuermer table
CREATE TABLE stuermer (
    id INTEGER PRIMARY KEY REFERENCES spieler(id),
    geschossene_tore INTEGER NOT NULL,
    schussgenauigkeit DOUBLE PRECISION NOT NULL,
    chancenverwertung DOUBLE PRECISION NOT NULL
);
