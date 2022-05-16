FROM openjdk:8-jdk-alpine
COPY ./target/collections-service-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
EXPOSE 8105
CMD ["java","-jar","./collections-service-0.0.1-SNAPSHOT.jar"]