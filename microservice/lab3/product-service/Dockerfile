FROM openjdk:8-jdk-alpine
COPY ./target/product-service-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
EXPOSE 8100
CMD ["java","-jar","./product-service-0.0.1-SNAPSHOT.jar"]