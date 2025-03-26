mvn clean spring-boot:run#!/bin/bash

echo "🚀 Starte Entwicklungsumgebung mit Docker..."
echo "Dies startet die PostgreSQL-Datenbank, pgAdmin, Adminer und die Entwicklungsversionen von Backend und Frontend"

docker-compose -f docker-compose.dev.yml up -d

echo "✅ Entwicklungsumgebung gestartet!"
echo "📊 PostgreSQL ist auf Port 5432 verfügbar"
echo "🔍 Adminer ist auf http://localhost:8081 verfügbar"
echo "🔧 pgAdmin ist auf http://localhost:8082 verfügbar"
echo "🔙 Backend ist auf http://localhost:8080 verfügbar"
echo "🖥️ Frontend ist auf http://localhost:3000 verfügbar"
echo ""
echo "🧪 Datenbank-Zugangsdaten:"
echo "  Server: db (oder localhost)"
echo "  Benutzer: postgres"
echo "  Passwort: postgres"
echo "  Datenbank: sportverein"
echo ""
echo "Um die Container zu stoppen, führen Sie aus: ./stop-dev.sh"
