#!/bin/bash

echo "🛑 Stoppe Datenbank-Admin-Tools..."

docker-compose -f docker-compose.admin.yml down

echo "✅ Admin-Tools gestoppt."
