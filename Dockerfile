FROM openjdk:17-alpine

ARG JAR_FILE=./build/libs/*.jar

COPY ${JAR_FILE} /app/rms.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "rms.jar"]