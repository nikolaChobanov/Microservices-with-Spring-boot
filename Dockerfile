FROM java:8
LABEL maintainer="nklchobanov@gmail.com"
EXPOSE 8080
ADD target/authentication-service-0.0.1-SNAPSHOT.jar sauthentication-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","authentication-service-0.0.1-SNAPSHOT.jar"]