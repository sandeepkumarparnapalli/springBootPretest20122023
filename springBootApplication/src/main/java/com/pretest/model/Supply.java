package com.pretest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class Supply {
    private String productId;
    private Double quantity;

    public Supply(String productId,Double quantity){
        this.productId = productId;
        this.quantity = quantity;
    }
}
