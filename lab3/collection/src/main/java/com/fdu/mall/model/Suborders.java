package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suborders {

  private long orderId;
  private long fatherorderId;
  private long goodsNum;
  private long amount;
  private String seller;
//  private String buyer;
//  private String status;
  private List<GoodsInSuborders> goods;


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getFatherorderId() {
    return fatherorderId;
  }

  public void setFatherorderId(long fatherorderId) {
    this.fatherorderId = fatherorderId;
  }


  public long getGoodsNum() {
    return goodsNum;
  }

  public void setGoodsNum(long goodsNum) {
    this.goodsNum = goodsNum;
  }


  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }


  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

//  public String getBuyer() {
//    return buyer;
//  }
//
//  public void setBuyer(String buyer) {
//    this.buyer = buyer;
//  }
//
//  public String getStatus() {
//    return status;
//  }
//
//  public void setStatus(String status) {
//    this.status = status;
//  }

  public List<GoodsInSuborders> getGoods() {
    return goods;
  }

  public void setGoods(List<GoodsInSuborders> goods) {
    this.goods = goods;
  }

}
