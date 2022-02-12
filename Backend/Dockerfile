FROM openjdk:11
ARG JAR_FILE=build/libs/sunin-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "${JAVA_OPTS}", "-jar", "/app.jar"]