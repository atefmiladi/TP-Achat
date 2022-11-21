FROM openjdk:14-alpine
RUN mkdir app
WORKDIR /app
COPY /target/tpAchatProject-1.0.0-RELEASE.jar tpAchatProject-1.0.0-RELEASE.jar  
EXPOSE 8089
RUN chmod 777 tpAchatProject-1.0.0-RELEASE.jar 
CMD ["java","-jar","tpAchatProject-1.0.0-RELEASE.jar"]