package com.example.demo.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.ResultSet;

@Data
@AllArgsConstructor
public class RestBean<T> {
    int code;
    String reason;
    T data;

    public RestBean(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
