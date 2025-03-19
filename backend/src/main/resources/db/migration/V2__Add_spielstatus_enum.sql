-- Erstellen eines Aufzählungstyps für den Spielstatus
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'spielstatus') THEN
        CREATE TYPE spielstatus AS ENUM (
            'GEPLANT',
            'LIVE', 
            'BEENDET', 
            'VERSCHOBEN', 
            'ABGESAGT'
        );
        
        -- Bestehende Tabellenspalte vom Typ VARCHAR auf den Enum-Typ umstellen
        ALTER TABLE spiel ALTER COLUMN status TYPE spielstatus USING status::spielstatus;
    END IF;
END
$$;
