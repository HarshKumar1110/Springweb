FROM openjdk:11-ea
COPY target/Springweb-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java",".jar","/app.jar"]