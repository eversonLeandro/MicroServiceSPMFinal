# Fase de construcción con JDK 21
FROM eclipse-temurin:21-jdk-jammy AS builder

# Instalar Maven manualmente
RUN apt-get update && \
    apt-get install -y maven curl && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

# Configurar Maven para Java 21
RUN chmod +x mvnw && \
    ./mvnw clean package -DskipTests \
    -Dmaven.compiler.source=21 \
    -Dmaven.compiler.target=21

# Fase de ejecución con JRE 21
FROM eclipse-temurin:21-jre-jammy

# Instalar curl para health check
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto (según application.yml)
EXPOSE 8086

# Configuración PostgreSQL (única base de datos)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-project:5432/project_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=123
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect

# Configuración RabbitMQ
ENV SPRING_RABBITMQ_HOST=rabbitmq
ENV SPRING_RABBITMQ_PORT=5672
ENV SPRING_RABBITMQ_USERNAME=guest
ENV SPRING_RABBITMQ_PASSWORD=guest

# Configuración Jackson (según application.yml)
ENV SPRING_JACKSON_SERIALIZATION_FAIL_ON_EMPTY_BEANS=false
ENV SPRING_JACKSON_SERIALIZATION_WRITE_DATES_AS_TIMESTAMPS=false

# Configuración Keycloak
ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI=http://keycloak:8080/realms/MicroserviceSPM
ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK_SET_URI=http://keycloak:8080/realms/MicroserviceSPM/protocol/openid-connect/certs

# Health check (actuator)
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8086/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]