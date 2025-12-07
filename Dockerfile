FROM eclipse-temurin:21-jdk-alpine

WORKDIR /target

COPY target/demo-0.0.1-SNAPSHOT.jar /target/demo.jar

ENTRYPOINT ["java", "-jar", "/target/demo.jar"]
