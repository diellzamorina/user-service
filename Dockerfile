FROM openjdk:17
COPY build/libs/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080