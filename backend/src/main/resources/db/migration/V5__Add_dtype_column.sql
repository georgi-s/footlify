-- Hinzufügen der DTYPE-Spalte zur Spieler-Tabelle für die JPA-Vererbungshierarchie
ALTER TABLE spieler ADD COLUMN IF NOT EXISTS dtype VARCHAR(31);

-- Aktualisieren der bestehenden Spieler mit einem Standardwert
-- Wir nehmen an, dass alle existierenden Spieler vom Typ 'SpielerEntity' sind
UPDATE spieler SET dtype = 'SpielerEntity' WHERE dtype IS NULL;
