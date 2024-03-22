FROM eclipse-temurin:17.0.8_7-jre
WORKDIR bmstu_team-dev
COPY target/test_init-0.0.1-SNAPSHOT.jar test_init-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "./test_init-0.0.1-SNAPSHOT.jar"]
