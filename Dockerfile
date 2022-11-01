FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tpAchatProject-0.0.4-SNAPSHOT.jar tpAchatProject-0.0.4-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-0.0.4-SNAPSHOT.jar"]