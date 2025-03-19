#!/bin/bash

echo "🔧 Starte Datenbank-Admin-Tools..."
echo "Dies startet Adminer und pgAdmin für die Verwaltung der Datenbank"
echo "Diese Tools können unabhängig von der Haupt-Anwendung genutzt werden."

docker-compose -f docker-compose.admin.yml up -d

echo "✅ Admin-Tools gestartet!"
echo "🔍 Adminer ist auf http://localhost:8081 verfügbar"
echo "🔧 pgAdmin ist auf http://localhost:8082 verfügbar"
echo ""
echo "🧪 Datenbank-Zugangsdaten für eine lokale PostgreSQL-Datenbank:"
echo "  Server: localhost"
echo "  Port: 5432"
echo "  Benutzer: postgres"
echo "  Passwort: postgres"
echo "  Datenbank: sportverein"
echo ""
echo "Um die Admin-Tools zu stoppen, führen Sie aus: ./stop-admin-tools.sh"
