-- Fehlende Spalten zur Spieler-Tabelle hinzufügen
ALTER TABLE spieler ADD COLUMN IF NOT EXISTS rote_karten INTEGER DEFAULT 0;
ALTER TABLE spieler ADD COLUMN IF NOT EXISTS gelbe_karten INTEGER DEFAULT 0;
ALTER TABLE spieler ADD COLUMN IF NOT EXISTS gespielte_spiele INTEGER DEFAULT 0;
ALTER TABLE spieler ADD COLUMN IF NOT EXISTS gesperrt BOOLEAN DEFAULT false;
ALTER TABLE spieler ADD COLUMN IF NOT EXISTS vereinsbeitritt DATE;

-- Position column ist bereits vorhanden und ersetzt spieler_type
-- Entfernen von Spalten, die nicht mehr benötigt werden
ALTER TABLE spieler DROP COLUMN IF EXISTS ist_feldspieler;
ALTER TABLE spieler DROP COLUMN IF EXISTS trikotnummer;
