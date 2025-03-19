# Backend Dockerfile
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /workspace/app

# Erst die Abhängigkeiten installieren (optimiert für Docker-Caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Danach den Quellcode kopieren
COPY src src

# Nun das Projekt bauen
RUN mvn clean install -DskipTests

# Zweiter Container für das Runtime-Environment
FROM openjdk:17-slim
WORKDIR /app

COPY --from=build /workspace/app/target/*.jar app.jar

# Port für Spring Boot App freigeben (nicht zwingend)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
