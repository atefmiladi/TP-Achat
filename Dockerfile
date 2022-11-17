FROM openjdk:14-alpine
RUN mkdir app
WORKDIR /app
COPY /target/tpAchatProject-1.0-Release.jar tpAchatProject-1.0-Release.jar  
EXPOSE 8089
RUN chmod 777 tpAchatProject-1.0-Release.jar 
CMD ["java","-jar","tpAchatProject-1.0-Release.jar"]