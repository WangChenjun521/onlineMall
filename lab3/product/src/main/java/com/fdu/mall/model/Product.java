package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer goodsId;
    private String name;
    private String descs;
    private String img;
    private Integer price;
    private String seller;



    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return descs;
    }

    public void setDesc(String desc) {
        this.descs = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }


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