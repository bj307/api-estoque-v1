package com.reagente.manager.manager.controler.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> errors;

    public ApiErros(List<String> errors){
        this.errors = errors;
    }

    public ApiErros(String message){
        this.errors = Arrays.asList(message);
    }
}
