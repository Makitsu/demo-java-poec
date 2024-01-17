package com.jmd.poec.java.demojpa2.domain.exception;

import org.springframework.http.HttpStatus;

public class WilderException extends RuntimeException {

    private HttpStatus statusCode;

    public WilderException(HttpStatus statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
