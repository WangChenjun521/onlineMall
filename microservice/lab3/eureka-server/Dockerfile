FROM openjdk:8-jdk-alpine
COPY ./target/eureka-server-v1.0.0.jar ./

WORKDIR ./
EXPOSE 8761
CMD ["java","-jar","./eureka-server-v1.0.0.jar"]