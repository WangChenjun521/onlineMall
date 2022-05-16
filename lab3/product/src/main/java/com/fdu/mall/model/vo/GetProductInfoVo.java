package com.fdu.mall.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductInfoVo {
    private Integer goodsId;
    private String name;
    private String desc;
    private String img;
    private Integer price;
    private String seller;
    private Integer specsNum=0;
    private List<Spec> specs;




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
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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