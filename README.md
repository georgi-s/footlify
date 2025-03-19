<div align="center">
  <h1 align="center">Footlify</h1>
  <p align="center">
    Eine moderne Webanwendung zur Verwaltung von Sportvereinen mit Fokus auf Fußballorganisationen
  </p>
  <p align="center">
    <strong>Open-Source</strong> · Spring Boot & React · <strong>Docker</strong>
  </p>
</div>

## Über das Projekt

Footlify ist eine umfassende Verwaltungslösung für Sportvereine, die es ermöglicht, Mannschaften, Spieler, Spielpläne und Turniere effizient zu verwalten. Das Projekt wurde mit einer modernen Architektur entwickelt, die ein Spring Boot Backend mit einem React Frontend kombiniert und durch Docker containerisiert wird.

**Funktionen**:

- 🏆 **Mannschaftsverwaltung**: Erstellen, Bearbeiten und Löschen von Mannschaften mit Trainer, Formation und Liga
- 👥 **Spielerverwaltung**: Komplette Verwaltung aller Spieler mit Position, Statistiken und Spielhistorie
- 📅 **Spielplanverwaltung**: Organisation von Spielen zwischen Mannschaften mit Datum, Ort und Ergebnissen
- 🏅 **Liga- und Turniersystem**: Verwaltung verschiedener Wettbewerbe und deren Zuordnung
- 📊 **Statistiken**: Übersicht über Spieler- und Mannschaftsleistungen (in Entwicklung)

## Inhaltsverzeichnis

1. [Technologiestack](#technologiestack)
2. [Voraussetzungen](#voraussetzungen)
3. [Installation und Entwicklung](#installation-und-entwicklung)
4. [Produktionsumgebung](#produktionsumgebung)
5. [Projektstruktur](#projektstruktur)
6. [API-Dokumentation](#api-dokumentation)
7. [Beitragen](#beitragen)
8. [Lizenz](#lizenz)

## Technologiestack

### Backend
- **Framework**: [Spring Boot](https://spring.io/projects/spring-boot) 3.4.3
- **Sprache**: Java 17
- **Datenbankzugriff**: [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- **Datenbank**: [PostgreSQL](https://www.postgresql.org/)
- **Migration**: [Flyway](https://flywaydb.org/)
- **Build-Tool**: [Maven](https://maven.apache.org/)

### Frontend
- **Laufzeitumgebung**: [Node.js](https://nodejs.org/)
- **Framework**: [React](https://reactjs.org/) 18
- **Sprache**: [TypeScript](https://www.typescriptlang.org/)
- **Build-Tool**: [Vite](https://vitejs.dev/)
- **HTTP-Client**: [Axios](https://axios-http.com/) für API-Anfragen
- **Routing**: [React Router](https://reactrouter.com/)
- **UI-Bibliotheken**: 
  - [Tailwind CSS](https://tailwindcss.com/)
  - [Radix UI](https://www.radix-ui.com/) Komponenten
  - [Framer Motion](https://www.framer.com/motion/) für Animationen

### Deployment
- **Containerisierung**: [Docker](https://www.docker.com/)
- **Orchestrierung**: [Docker Compose](https://docs.docker.com/compose/)
- **Server**: [Nginx](https://nginx.org/) (Frontend)

## Voraussetzungen

Um das Projekt lokal zu entwickeln oder zu betreiben, benötigen Sie:

- [Docker](https://www.docker.com/) und [Docker Compose](https://docs.docker.com/compose/install/)
- [Java 17](https://adoptium.net/) oder höher (für lokale Backend-Entwicklung)
- [Node.js](https://nodejs.org/) 18 oder höher (für lokale Frontend-Entwicklung)
- [Git](https://git-scm.com/) für Versionskontrolle

## Installation und Entwicklung

### Entwicklungsumgebung starten

1. **Repository klonen**:
   ```bash
   git clone <repository-url>
   cd footlify
   ```

2. **Entwicklungsumgebung mit Docker starten**:
   ```bash
   ./start-dev.sh
   ```
   Dieser Befehl startet die Backend- und Frontend-Services sowie eine PostgreSQL-Datenbank.

3. **Zugriff auf die Anwendung**:
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080/api
   - Adminer (Datenbank-Verwaltung): http://localhost:8081

### Manuelle Entwicklung (ohne Docker)

#### Backend:
```bash
cd backend
./mvnw spring-boot:run
```

#### Frontend:
```bash
cd frontend
npm install
npm run dev
```

## Produktionsumgebung

1. **Produktionsumgebung starten**:
   ```bash
   ./start-prod.sh
   ```

2. **Produktionsumgebung stoppen**:
   ```bash
   ./stop-prod.sh
   ```

## Projektstruktur

```
footlify/
├── backend/               # Spring Boot Backend
│   ├── src/               # Quellcode
│   │   ├── main/java/com/sportverein/
│   │   │   ├── controller/   # REST-Controller
│   │   │   ├── entity/       # Datenbankentitäten
│   │   │   ├── dto/          # Data Transfer Objects
│   │   │   ├── service/      # Business-Logik
│   │   │   └── repository/   # Datenzugriffslayer
│   │   └── resources/     # Konfigurationsdateien
│   ├── Dockerfile         # Produktions-Docker-Konfiguration
│   └── Dockerfile.dev     # Entwicklungs-Docker-Konfiguration
├── frontend/              # React/TypeScript Frontend
│   ├── src/               # Quellcode
│   │   ├── components/    # Wiederverwendbare Komponenten
│   │   ├── hooks/         # React Hooks
│   │   └── services/      # API-Service-Klassen (Axios)
│   ├── Dockerfile         # Produktions-Docker-Konfiguration
│   └── Dockerfile.dev     # Entwicklungs-Docker-Konfiguration
├── docker-compose.yml           # Hauptkonfiguration
├── docker-compose.dev.yml       # Entwicklungskonfiguration
├── docker-compose.admin.yml     # Admin-Tools-Konfiguration
└── start-*.sh                   # Hilfsskripte zum Starten/Stoppen
```

## API-Dokumentation

Das Backend bietet REST-APIs für die Verwaltung von Mannschaften, Spielern und Spielen. Die API-Endpunkte sind:

- **Mannschaften**: `/api/mannschaften`
- **Spieler**: `/api/spieler`
- **Spiele**: `/api/spiele`

### Beispiel für eine API-Anfrage mit Axios

```typescript
import axios from 'axios';

// GET-Anfrage zum Abrufen aller Mannschaften
const fetchTeams = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/mannschaften');
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error('Fehler beim Abrufen der Mannschaften:', error);
    throw error;
  }
};

// POST-Anfrage zum Erstellen einer neuen Mannschaft
const createTeam = async (teamData) => {
  try {
    const response = await axios.post('http://localhost:8080/api/mannschaften', teamData);
    console.log('Mannschaft erstellt:', response.data);
    return response.data;
  } catch (error) {
    console.error('Fehler beim Erstellen der Mannschaft:', error);
    throw error;
  }
};
```

## Beitragen

Beiträge zum Projekt sind willkommen! Hier ist, wie Sie beitragen können:

1. **Fork des Repositorys**
2. **Feature-Branch erstellen**: `git checkout -b feature/neue-funktion`
3. **Änderungen committen**: `git commit -am 'Neue Funktion hinzufügen'`
4. **Branch pushen**: `git push origin feature/neue-funktion`
5. **Pull Request erstellen**

Bitte stellen Sie sicher, dass Ihr Code den Projektstandards entspricht und dass alle Tests bestanden werden.

## Lizenz

[Hier Lizenzinformationen einfügen]