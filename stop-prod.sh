#!/bin/bash

echo "🛑 Stoppe Produktionsumgebung..."

docker-compose down

echo "✅ Produktionsumgebung gestoppt."
echo "Die Datenbank-Daten bleiben in Docker-Volumes erhalten."
