# Build stage with JDK 17
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Runtime stage with JRE 17
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Expose gateway port
EXPOSE 8081

# Gateway routes configuration (can be overridden with env vars if needed)
ENV SPRING_CLOUD_GATEWAY_ROUTES_0_ID=project-service
ENV SPRING_CLOUD_GATEWAY_ROUTES_0_URI=http://project-service:8086
ENV SPRING_CLOUD_GATEWAY_ROUTES_0_PREDICATES_0=Path=/apiProject/**

ENV SPRING_CLOUD_GATEWAY_ROUTES_1_ID=postulation-service
ENV SPRING_CLOUD_GATEWAY_ROUTES_1_URI=http://postulation-service:8082
ENV SPRING_CLOUD_GATEWAY_ROUTES_1_PREDICATES_0=Path=/postulaciones/**

ENV SPRING_CLOUD_GATEWAY_ROUTES_2_ID=company-service
ENV SPRING_CLOUD_GATEWAY_ROUTES_2_URI=http://company-service:8088
ENV SPRING_CLOUD_GATEWAY_ROUTES_2_PREDICATES_0=Path=/apiCompanies/**

ENV SPRING_CLOUD_GATEWAY_ROUTES_3_ID=student-service
ENV SPRING_CLOUD_GATEWAY_ROUTES_3_URI=http://student-service:8083
ENV SPRING_CLOUD_GATEWAY_ROUTES_3_PREDICATES_0=Path=/api/Students/**

ENV SPRING_CLOUD_GATEWAY_ROUTES_4_ID=login-service
ENV SPRING_CLOUD_GATEWAY_ROUTES_4_URI=http://login-service:8085
ENV SPRING_CLOUD_GATEWAY_ROUTES_4_PREDICATES_0=Path=/api/usuarios/**

# Keycloak/JWT configuration
ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI=http://keycloak:8080/realms/MicroserviceSPM
ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK_SET_URI=http://keycloak:8080/realms/MicroserviceSPM/protocol/openid-connect/certs

# Health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]