FROM openjdk:14-alpine
RUN mkdir app
WORKDIR /app
COPY /target/tpAchatProject-3.0.0-SNAPSHOT.jar tpAchatProject-3.0.0-SNAPSHOT.jar  
EXPOSE 8089
RUN chmod 777 tpAchatProject-3.0.0-SNAPSHOT.jar 
CMD ["java","-jar","tpAchatProject-3.0.0-SNAPSHOT.jar"]