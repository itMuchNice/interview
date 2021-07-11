package com.prudential.carRental.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDTO {
    private String returnCode;
    private String message;
    private String resultData;
}
