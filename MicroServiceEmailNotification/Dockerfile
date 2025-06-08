# Fase de construcción
FROM eclipse-temurin:17-jdk-jammy AS builder

# Instalar Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

# Construir el proyecto sin tests
RUN mvn clean package -DskipTests

# Fase de ejecución
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copiar el jar generado
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto (según application.yml)
EXPOSE 8089

# Healthcheck opcional
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8089/actuator/health || exit 1

# Ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]
