FROM mysql:latest


ENV MYSQL_ROOT_PASSWORD =root
ENV MYSQL_DATA_BASE =mydatabase
ENV MYSQL_USER =myuser
ENV MYSQL_PASSWORD =mypassword

COPY init.sql /docker-entrypoint-initdb.d/


EXPOSE 3306