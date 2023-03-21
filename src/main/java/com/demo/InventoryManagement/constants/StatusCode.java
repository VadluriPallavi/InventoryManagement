package com.demo.InventoryManagement.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {
    SUCCESS(200, "Success"),

    FAILURE(600, "Failed"),

    NOT_FOUND(404, "Not found");

    private final int code;
    private final String message;
}
