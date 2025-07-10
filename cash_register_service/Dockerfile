# Stage 1: Build JAR
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run app
# FROM eclipse-temurin:17-jdk-alpine
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /build/target/cash_register_service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]