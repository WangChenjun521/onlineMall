package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String desc;
    private String img;
}



/*
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(20) DEFAULT NULL,
 `desc` varchar(255) DEFAULT NULL,
 `picture` varchar(255) DEFAULT NULL,
 `price` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT  CHARSET=utf8;
 */