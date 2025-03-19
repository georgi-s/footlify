# Frontend Build Stage
FROM node:18-alpine AS build
WORKDIR /app

# Umgebungsvariablen für bessere Performance
ENV NODE_ENV=production

# Abhängigkeiten installieren
COPY package*.json ./
RUN npm ci

# Quellcode kopieren und bauen
COPY . .
ARG REACT_APP_API_URL=http://localhost:8080/api
ENV REACT_APP_API_URL=$REACT_APP_API_URL
RUN npm run build

# Production Stage mit Nginx
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Nginx Healthcheck
HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget --quiet --tries=1 --spider http://localhost:80/ || exit 1

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
