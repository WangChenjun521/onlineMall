package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String username;
    private Integer goodsId;
    private Integer num;
    private String specName;
    private String img;
    private String name;
    private String seller;
    private Float price;
}


//CREATE TABLE `cart` (
//        `username` varchar(20) not null,
//        `goodsId` int(255) not null,
//        `num` int(255) not null,
//        `specName` varchar(50) not null,
//        `img` varchar(100) default null,
//        `name` varchar(50) default null,
//        `seller` varchar(50) default null,
//        `price` float(255) default null,
//        primary key (`username`, `goodsId`, `specName`)
//        ) ENGINE=MyISAM DEFAULT CHARSET=utf8;


