package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Collections {
    private String username ;
    private Integer goodsId ;
    private Integer num;
    private String specName;
    private String img;
    private String name;
    private String seller;
    private Float price;
}

//    CREATE TABLE `collections` (
//        `username` varchar(255) NOT NULL,
//        `goodsId` int NOT NULL,
//        `num` int DEFAULT NULL,
//        `specName` varchar(255) DEFAULT NULL,
//        `img` varchar(255) DEFAULT NULL,
//        `name` varchar(255) DEFAULT NULL,
//        `seller` varchar(255) DEFAULT NULL,
//        `price` decimal(10,2) DEFAULT NULL
//        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
//    INSERT INTO `collections` VALUES ('test', 1, 1, '', 'http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2xocxdgj20kt0nmacs.jpg', 'phone', 'seller1', 5000);