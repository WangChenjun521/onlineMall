create database `lab2`;

use lab2;

create table user
(
    id        int auto_increment
        primary key,
    username  varchar(20)  null,
    nickname  varchar(20)  null,
    password  varchar(20)  null,
    addressee varchar(20)  null,
    address   varchar(100) null,
    phone     varchar(20)  null,
    authority int          null
)
    charset = utf8;


