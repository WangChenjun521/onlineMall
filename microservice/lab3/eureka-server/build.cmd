docker build -t eureka .
docker run -p 8761:8761 --name eureka-service -d eureka 