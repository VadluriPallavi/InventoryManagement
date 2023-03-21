package com.demo.InventoryManagement.models;

import lombok.Builder;

@Builder
public class Response<T> {

    private Integer responseCode;

    private String responseMessage;

    private T response;
}
