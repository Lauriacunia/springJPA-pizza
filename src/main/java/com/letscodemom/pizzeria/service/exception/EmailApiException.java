package com.letscodemom.pizzeria.service.exception;

public class EmailApiException extends RuntimeException{
    public EmailApiException(String message) {
        super(message);
    }
}
