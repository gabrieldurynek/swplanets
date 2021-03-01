FROM openjdk
    ADD target/swplanets-0.0.1-SNAPSHOT.jar swplanets.jar
    ENTRYPOINT ["java", "-jar", "swplanets.jar"]