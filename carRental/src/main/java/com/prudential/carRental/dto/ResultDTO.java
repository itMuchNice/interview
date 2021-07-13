package com.prudential.carRental.dto;


import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDTO {
    private String returnCode;
    private String message;
    private JSON resultData;
}
