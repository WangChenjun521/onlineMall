package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

  private long orderId;
  private String buyer;
  private String status;
  private long state;
  private long phone;
  private String address;
  private String comReason;
  private java.sql.Timestamp createtime;
  private long amount;
  private List<Suborders> subOrders;


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


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public long getPhone() {
    return phone;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getComReason() {
    return comReason;
  }

  public void setComReason(String comReason) {
    this.comReason = comReason;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }


  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public List<Suborders> getSubOrders() {
    return subOrders;
  }

  public void setSubOrders(List<Suborders> subOrders) {
    this.subOrders = subOrders;
  }

}
