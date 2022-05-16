package com.fdu.mall.model;

import lombok.Data;

import java.util.List;

@Data
public class CartDeleteParams {
    public String username;
    public List<CartDelete> deleteList;
}
