FROM mysql:5.7
COPY ./*.sql ./
COPY ./my.cnf ./
RUN mkdir -p /usr/data/mysql/logs /usr/data/mysql/data /usr/data/mysql/conf
COPY ./start/sh ./
CMD ["./start.sh"]