package com.pretest.service;

import com.pretest.model.Demand;
import com.pretest.model.ProductDetailsInput;
import com.pretest.model.Supply;
import com.pretest.model.SupplyWithDate;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceClass {

    //hardcoding supply data to a list
    private List<Supply> supplyList = new ArrayList<>(Arrays.asList(
            new Supply("Product1",10.0),
            new Supply("Product2",5.0)
    ));

    //hardcoding demand data to a list
    private List<Demand> demandList = new ArrayList<>(Arrays.asList(
            new Demand("Product1",2.0),
            new Demand("Product2",5.0)
    ));


    private List<SupplyWithDate> supplyWithDates;
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            supplyWithDates = new ArrayList<>(Arrays.asList(
                    new SupplyWithDate("Product1",sdf.parse("2021-03-16T08:53:48.616Z"),10.0),
                    new SupplyWithDate("Product2",sdf.parse("2021-03-16T08:59:48.616Z"),5.0),
                    new SupplyWithDate("Product3",sdf.parse("2021-03-16T09:10:48.616Z"),30.0),
                    new SupplyWithDate("Product4",sdf.parse("2021-03-16T09:10:48.616Z"),20.0)
            ));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getProductDetails(ProductDetailsInput productDetailsInput){
        String productId = productDetailsInput.getProductId();
        Double supplyValue = supplyList.stream()
                .filter(s -> s.getProductId().equalsIgnoreCase(productId))
                .findFirst().get().getQuantity();
        Double demandValue = demandList.stream()
                .filter(d -> d.getProductId().equalsIgnoreCase(productId))
                .findFirst().get().getQuantity();
        Double availability = supplyValue - demandValue;

        if(availability==0.0){
            return "204 No Content";
        }else{
            JSONObject jsonOutput = new JSONObject();
            jsonOutput.put("productId",productId);
            jsonOutput.put("Availability",availability);
            return jsonOutput.toJSONString();
        }
    }

    public SupplyWithDate updateSupply(SupplyWithDate supplyWithDate){
        Date timeStampOfInputRecord = supplyWithDate.getUpdateTimeStamp();
        String productId = supplyWithDate.getProductId();
        Double qty = supplyWithDate.getQuantity();
        Date timeStampOfMatchingRecord = supplyWithDates.stream()
                .filter(s -> s.getProductId().equalsIgnoreCase(productId))
                .findFirst().get().getUpdateTimeStamp();

        String status = "";
        if(timeStampOfInputRecord.compareTo(timeStampOfMatchingRecord) < 0){
            //Out of Sync Update
            status = "Out Of Sync Update";
        }else if(timeStampOfInputRecord.compareTo(timeStampOfMatchingRecord) > 0){
            Double quantityOfMatchingRecord =
                    supplyWithDates.stream().filter(s -> s.getProductId().equalsIgnoreCase(productId))
                            .findFirst().get().getQuantity();
            qty = qty+quantityOfMatchingRecord;
            status = "Updated";
        }

        SupplyWithDate outputValue = new SupplyWithDate(productId,timeStampOfInputRecord,qty,status);
        return outputValue;
    }


}
