# Compiles the application
FROM maven:3.9.7-eclipse-temurin-21-alpine AS build

WORKDIR /app
COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

# Final lightweight image for execution only
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
