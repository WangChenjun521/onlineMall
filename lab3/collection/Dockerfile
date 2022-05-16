FROM registry.cn-hangzhou.aliyuncs.com/xywu/openjdk:8-jre-slim
RUN apt update && apt upgrade -y && apt install maven -y
COPY ./* ./
WORKDIR ./
RUN mvn clean package
CMD ["java","-jar","./target/lab2.jar"]