# Datenbankmigrationen mit Flyway

Dieses Projekt verwendet Flyway für Datenbankmigrationen, um die Schemaänderungen zu verwalten.

## Migrationsstruktur

1. **V1__Create_base_schema.sql** - Erstellt das Grundschema der Datenbank
2. **V2__Add_spielstatus_enum.sql** - Wandelt das Statusfeld in einen Enum-Datentyp um
3. **V3__Insert_sample_data.sql** - Fügt Beispieldaten in die Datenbank ein

## Wie es funktioniert

1. Wenn die Anwendung startet, überprüft Flyway die Datenbank.
2. Falls die Datenbank noch nicht existiert oder leer ist, werden alle Migrationsskripte ausgeführt.
3. Falls die Datenbank bereits existiert, führt Flyway nur die Skripte aus, die noch nicht angewendet wurden.

## Neue Migrationen hinzufügen

Um neue Datenbankänderungen hinzuzufügen:

1. Erstelle eine neue SQL-Datei im Verzeichnis `src/main/resources/db/migration`
2. Benenne die Datei nach dem Muster `V{NächsteVersionsNummer}__{BeschreibenderName}.sql`
3. Füge die SQL-Befehle in die Datei ein

Beispiel:
```
V4__Add_statistik_table.sql
```

## Baselines

Wenn Sie eine bestehende Datenbank verwenden, können Sie Flyway mitteilen, dass es bestimmte Migrationen nicht ausführen soll, da der Datenbankstatus bereits diese Version erreicht hat. Dies geschieht mit dem Befehl:

```
flyway baseline -baselineVersion=3
```

Dies würde Flyway sagen, die Versionen 1-3 zu überspringen und nur neuere zu berücksichtigen.

In der `application.yml` ist `spring.flyway.baseline-on-migrate: true` gesetzt, was bedeutet, dass Flyway automatisch eine Baseline erstellt, wenn es auf eine nicht-leere Datenbank trifft.

## Besondere Hinweise

- **Niemals** bestehende Migrationsdateien ändern, nachdem sie in eine gemeinsame Codebase eingecheckt wurden
- Bei großen Schemaänderungen ist es ratsam, diese in mehrere kleine Migrationen aufzuteilen
- Testen Sie Migrationen immer in einer Testumgebung, bevor Sie sie auf Produktionsumgebungen anwenden
