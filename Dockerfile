FROM openjdk:17-alpine

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} /app/rms.jar

EXPOSE 8080

CMD ["java", "-jar", "rms.jar"]