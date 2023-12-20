package com.pretest.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Demand {
    private String productId;
    private Double quantity;

    public Demand(String productId,Double quantity){
        this.productId = productId;
        this.quantity = quantity;
    }
}
