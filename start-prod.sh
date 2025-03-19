#!/bin/bash

echo "🚀 Starte Produktionsumgebung mit Docker..."
echo "Dies startet die PostgreSQL-Datenbank, das Backend, das Frontend und Adminer in Produktionskonfiguration"

docker-compose up -d

echo "✅ Produktionsumgebung gestartet!"
echo "📊 PostgreSQL ist auf Port 5432 verfügbar"
echo "🔍 Adminer ist auf http://localhost:8081 verfügbar"
echo "🔙 Backend ist auf http://localhost:8080 verfügbar"
echo "🖥️ Frontend ist auf http://localhost verfügbar"
echo ""
echo "Um die Container zu stoppen, führen Sie aus: ./stop-prod.sh"
