package com.pretest.controller;

import com.pretest.model.ProductDetailsInput;
import com.pretest.model.Supply;
import com.pretest.model.SupplyWithDate;
import com.pretest.service.ApplicationServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationControllerClass {

    @Autowired
    private ApplicationServiceClass applicationServiceClass;

    //http://localhost:8080/getAvailability
    @PostMapping("/getAvailability")
    public String getAvailability(@RequestBody ProductDetailsInput productDetailsInput){
        return applicationServiceClass.getProductDetails(productDetailsInput);
    }


    //http://localhost:8080/updateSupply
    @PostMapping("/updateSupply")
    public SupplyWithDate updateSupply(@RequestBody SupplyWithDate supplyWithDate){
        return applicationServiceClass.updateSupply(supplyWithDate);
    }
}
