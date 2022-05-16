FROM openjdk:8-jdk-alpine
COPY ./target/orders-service-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
EXPOSE 8095
CMD ["java","-jar","./orders-service-0.0.1-SNAPSHOT.jar"]