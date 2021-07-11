package com.prudential.carRental.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class BindingResultUtil {

    public static String processBindingResult(BindingResult bindingResult) {
        if (bindingResult == null || !bindingResult.hasErrors()) return "";

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            sb.append(error.getDefaultMessage() + ";");
        }
        return sb.toString();
    }
}
