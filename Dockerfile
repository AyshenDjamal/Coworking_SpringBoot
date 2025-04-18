# Base image
FROM openjdk:17-jdk-slim

# Jar faylı əlavə edilir
ARG JAR_FILE=target/spring-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Portu açır
EXPOSE 8080

# App-i işə salır
ENTRYPOINT ["java","-jar","/app.jar"]
