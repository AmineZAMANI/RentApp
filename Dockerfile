
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY web/pom.xml web/
COPY . .
RUN mvn clean package -pl web -am -DskipTests
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/web/target/web-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
