package com.pretest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class SupplyWithDate {
    private String productId;
    private Date updateTimeStamp;
    private Double quantity;

    @Nullable
    private String status;

    public SupplyWithDate(){
    }
    public SupplyWithDate(String productId,Date updateTimeStamp,Double quantity) throws ParseException {
        this.productId = productId;
        this.updateTimeStamp = updateTimeStamp;
        this.quantity = quantity;
    }

}
