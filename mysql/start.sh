mysql -v /usr/data/mysql/conf:/etc/mysql  -v /usr/data/mysql/logs:/var/log/mysql -v /usr/data/mysql/data:/var/lib/mysql -v /usr/data/mysql/my.cnf:/etc/mysql/my.cnf -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
&& source *.sql