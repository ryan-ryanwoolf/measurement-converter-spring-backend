package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TemperatureService {

    public static final String TEMPERATURE_UNITS_KELVINS = "kelvins";
    public static final String TEMPERATURE_UNITS_DEGREES_CELSIUS = "degrees celsius";
    public static final String TEMPERATURE_UNITS_FAHRENHEIT = "fahrenheit";


    public float convertMeasurement(float fromAmount, String unitFrom,String unitTo) throws InvalidCalculationException, InvalidMeasurementException {
        float convertedToSIUnit = convertToSIUnit(fromAmount,unitFrom);
        if(convertedToSIUnit<0){
            throw new InvalidMeasurementException("The amount requested was below the lowest valid temperature");
        }
        return convertFromSIUnit(convertedToSIUnit,unitTo);
    }

    public float convertToSIUnit(float fromAmount,String unitFrom) throws InvalidCalculationException {
        switch(unitFrom) {
            case TEMPERATURE_UNITS_FAHRENHEIT:
                //calculation
                return (float) ((fromAmount - 32) * 5/9 + 273.15);
            case TEMPERATURE_UNITS_DEGREES_CELSIUS:
                //calculation
                return fromAmount + 273.15F;
            case TEMPERATURE_UNITS_KELVINS:
                return fromAmount;
            default:
                throw new InvalidCalculationException("The unit from provided is not applicable for a temperature conversion");
        }
    }

    public float convertFromSIUnit(float siAmount,String unitTo) throws InvalidCalculationException {
        switch(unitTo) {
            case TEMPERATURE_UNITS_FAHRENHEIT:
                //calculation
                return (float) ((siAmount - 273.15) * 9/5 + 32);
            case TEMPERATURE_UNITS_DEGREES_CELSIUS:
                //calculation
                return siAmount - 273.15F;
            case TEMPERATURE_UNITS_KELVINS:
                return siAmount;
            default:
                throw new InvalidCalculationException("The unit to provided is not applicable for a temperature conversion");
        }
    }

}
