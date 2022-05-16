FROM openjdk:8-jdk-alpine
COPY ./target/gateway-v1.0.0.jar ./

WORKDIR ./
EXPOSE 8085
CMD ["java","-jar","./gateway-v1.0.0.jar"]