package com.ryanwoolf.measurementconverter.ExceptionHandling;

public class InvalidCalculationException extends Exception {

    public InvalidCalculationException(){
        super();
    }

    public InvalidCalculationException(String message){
        super(message);
    }


}
