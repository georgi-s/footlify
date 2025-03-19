#!/bin/bash

echo "🛑 Stoppe Entwicklungsumgebung..."

docker-compose -f docker-compose.dev.yml down

echo "✅ Entwicklungsumgebung gestoppt."
echo "Die Datenbank-Daten bleiben in Docker-Volumes erhalten."
