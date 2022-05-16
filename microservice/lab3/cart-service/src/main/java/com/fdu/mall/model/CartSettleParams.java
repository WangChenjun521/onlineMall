package com.fdu.mall.model;

import lombok.Data;

import java.util.List;

@Data
public class CartSettleParams {
    public String username;
    public List<CartSettle> settleList;
}
