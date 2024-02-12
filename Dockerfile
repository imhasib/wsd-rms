FROM openjdk:17-alpine

COPY ./build/libs/rms-0.0.1-SNAPSHOT.jar /app/rms.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "rms.jar"]