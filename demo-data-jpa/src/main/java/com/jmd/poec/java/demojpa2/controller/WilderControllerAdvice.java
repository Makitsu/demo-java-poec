package com.jmd.poec.java.demojpa2.controller;

import com.jmd.poec.java.demojpa2.domain.dto.ErrorDTO;
import com.jmd.poec.java.demojpa2.domain.exception.UserException;
import com.jmd.poec.java.demojpa2.domain.exception.WilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WilderControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(WilderControllerAdvice.class);

    @ExceptionHandler(WilderException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleWilderException(WilderException exception){
        LOGGER.error("Exception => WilderException : "+exception.getMessage(),exception);
        ErrorDTO errorDTO = new ErrorDTO(exception.getStatusCode(),exception.getMessage(),exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(errorDTO);
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleWilderException(UserException exception){
        LOGGER.error("Exception => UserException : "+exception.getMessage(),exception);
        ErrorDTO errorDTO = new ErrorDTO(exception.getStatusCode(),exception.getMessage(),exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(errorDTO);
    }


}
