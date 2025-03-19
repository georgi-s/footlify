# Docker-Konfiguration für Sportverein-Anwendung

Diese Dokumentation erklärt die Docker-Konfiguration für die Sportverein-Anwendung. Die Anwendung besteht aus:

- Spring Boot Backend mit PostgreSQL-Datenbank
- React Frontend
- Datenbank-Verwaltungstools (Adminer, pgAdmin)

## Verfügbare Docker-Konfigurationen

### 1. Produktionsumgebung (`docker-compose.yml`)

Die Standardkonfiguration für die Ausführung der Anwendung in einer Produktionsumgebung.

```bash
# Starten der Produktionskonfiguration
./start-prod.sh

# Stoppen der Produktionskonfiguration
./stop-prod.sh
```

### 2. Entwicklungsumgebung (`docker-compose.dev.yml`)

Speziell konfiguriert für die Entwicklung, mit Hot-Reloading für Backend und Frontend.

```bash
# Starten der Entwicklungsumgebung
./start-dev.sh

# Stoppen der Entwicklungsumgebung
./stop-dev.sh
```

### 3. Nur Admin-Tools (`docker-compose.admin.yml`)

Startet nur die Datenbank-Verwaltungstools, wenn Sie an einer lokalen Instanz arbeiten.

```bash
# Starten der Admin-Tools
./start-admin-tools.sh

# Stoppen der Admin-Tools
./stop-admin-tools.sh
```

## Datenbankzugriff

### Mit Adminer (einfach)

1. Öffnen Sie http://localhost:8081 im Browser
2. Melden Sie sich mit den folgenden Daten an:
   - System: PostgreSQL
   - Server: db (in Docker-Umgebung) oder localhost (lokale DB)
   - Benutzer: postgres
   - Passwort: postgres
   - Datenbank: sportverein

### Mit pgAdmin (fortgeschritten)

1. Öffnen Sie http://localhost:8082 im Browser
2. Melden Sie sich mit den folgenden Daten an:
   - E-Mail: admin@example.com
   - Passwort: admin
3. Fügen Sie einen neuen Server hinzu:
   - Name: SportvereinDB
   - Host: db (wichtig: bei Ausführung in Docker-Containern)
   - Port: 5432
   - Benutzer: postgres
   - Passwort: postgres
   - Datenbank: sportverein

## Docker-Konfigurationsdateien

- `docker-compose.yml` - Produktionskonfiguration
- `docker-compose.dev.yml` - Entwicklungskonfiguration
- `docker-compose.admin.yml` - Nur Datenbank-Verwaltungstools
- `backend/Dockerfile` - Backend-Produktion
- `backend/Dockerfile.dev` - Backend-Entwicklung
- `frontend/Dockerfile` - Frontend-Produktion
- `frontend/Dockerfile.dev` - Frontend-Entwicklung

## Spring Boot Profile

Die Anwendung unterstützt verschiedene Profile:

- `dev` - Entwicklungsumgebung (Standard in docker-compose.dev.yml)
- `prod` - Produktionsumgebung (Standard in docker-compose.yml)

## Datenbank-Volumes

Die Datenbank-Daten werden in Docker-Volumes gespeichert und bleiben auch nach dem Stoppen der Container erhalten. Um alle Daten zu löschen und von vorne zu beginnen:

```bash
# Produktionsumgebung
docker volume rm stdm_gruppec_scpm06_postgres_data

# Entwicklungsumgebung
docker volume rm stdm_gruppec_scpm06_postgres_data_dev

# pgAdmin-Konfiguration
docker volume rm stdm_gruppec_scpm06_pgadmin_data
```
