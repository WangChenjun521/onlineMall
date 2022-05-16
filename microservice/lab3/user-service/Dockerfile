FROM openjdk:8-jdk-alpine
COPY ./target/user-service-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
EXPOSE 8090
CMD ["java","-jar","./user-service-0.0.1-SNAPSHOT.jar"]

