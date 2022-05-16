package com.fdu.mall.model;

import lombok.Data;

@Data
public class Spec {
    public String specName;
    public Double price;

    public Spec() {
        specName = "x1";
        price = 1.0;
    }
}
