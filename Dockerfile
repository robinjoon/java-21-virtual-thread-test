FROM openjdk:21

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djdk.tracePinnedThreads=full","-jar", "/app.jar"]
