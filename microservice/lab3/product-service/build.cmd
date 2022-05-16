docker build  -t productservice . 
docker run -p 8100:8100 --name productservice_container -d productservice 