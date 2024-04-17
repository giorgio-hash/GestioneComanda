FROM openjdk:latest
COPY target/GestioneComanda-0.0.1-SNAPSHOT.jar GestioneComanda-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","GestioneComanda-0.0.1-SNAPSHOT.jar"]