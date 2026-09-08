package com.zgyz.ecny;


import lombok.Data;

@Data
public class CheckoutPayRequest {
    private String busiMainId;
    private String reqTransTime;
    private BusiData data;
}
