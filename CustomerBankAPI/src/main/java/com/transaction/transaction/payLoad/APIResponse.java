package com.transaction.transaction.payLoad;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    private String message;
    private T data;
    private boolean status;

    public APIResponse(String message,  boolean status) {
        this.message = message;
        this.status = status;
    }



}
