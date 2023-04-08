package com.ryanwoolf.measurementconverter.ExceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMeasurementException extends Exception {

    public InvalidMeasurementException(){
        super();
    }

    public InvalidMeasurementException(String message){
        super(message);
    }


}
