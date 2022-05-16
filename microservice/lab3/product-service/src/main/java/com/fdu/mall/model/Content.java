package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Content {

    private long orderId;
    private String buyer;
    private long goodsNum;
    private List<GoodsInSuborders> children;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }


    public long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(long goodsNum) {
        this.goodsNum = goodsNum;
    }


    public List<GoodsInSuborders> getChildren() {
        return children;
    }

    public void setChildren(List<GoodsInSuborders> children) {
        this.children = children;
    }

}