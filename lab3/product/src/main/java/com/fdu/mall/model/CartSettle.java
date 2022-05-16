package com.fdu.mall.model;

import lombok.Data;

@Data
public class CartSettle {
    public Integer goodsId;
    public String specName;
    public Integer num;
    public Float price;
}
