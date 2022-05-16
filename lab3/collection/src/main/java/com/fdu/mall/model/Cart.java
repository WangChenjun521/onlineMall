package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String nickname;
    private Integer id;
    private String img;
    private String name;
    private Integer goodsDetailId;
    private String spec;
    private Float unitPrice;
    private Integer goodsNum;
}
