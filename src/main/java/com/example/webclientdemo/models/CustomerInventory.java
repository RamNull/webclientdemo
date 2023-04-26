package com.example.webclientdemo.models;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerInventory{

    private String productId;
    private String productName;
    private String productType;
    private String productImageUrl;
    private long price;
    private int units;
    private Map<String,?> additionalDetails;
}