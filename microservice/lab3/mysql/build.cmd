docker build -t mysql .
docker run -p 3306:3306 --name themysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql