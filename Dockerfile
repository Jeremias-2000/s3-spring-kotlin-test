FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

COPY target/s3-kotlin-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
