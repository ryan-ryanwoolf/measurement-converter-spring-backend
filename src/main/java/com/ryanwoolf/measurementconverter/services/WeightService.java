package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WeightService {

    public static final String WEIGHT_UNITS_KILOGRAMS = "kilograms";
    public static final String WEIGHT_UNITS_POUNDS = "pounds";
    public static final String WEIGHT_UNITS_GRAMS = "grams";


    public float convertMeasurement(float fromAmount, String unitFrom,String unitTo) throws InvalidCalculationException, InvalidMeasurementException {
        float convertedToSIUnit = convertToSIUnit(fromAmount,unitFrom);
        return convertFromSIUnit(convertedToSIUnit,unitTo);
    }

    public float convertToSIUnit(float fromAmount,String unitFrom) throws InvalidCalculationException {
        switch(unitFrom) {
            case WEIGHT_UNITS_GRAMS:
                //calculation
                if(fromAmount!=0){
                    return fromAmount / 1000;
                }
                else
                {
                    return 0;
                }

            case WEIGHT_UNITS_POUNDS:
                //calculation
                if(fromAmount!=0){
                    return fromAmount / 2.205F;
                }
                else
                {
                    return 0;
                }

            case WEIGHT_UNITS_KILOGRAMS:
                return fromAmount;
            default:
                throw new InvalidCalculationException("The unit from provided is not applicable for a temperature conversion");
        }
    }

    public float convertFromSIUnit(float siAmount,String unitTo) throws InvalidCalculationException {
        switch(unitTo) {
            case WEIGHT_UNITS_GRAMS:
                //calculation
                return siAmount * 1000;
            case WEIGHT_UNITS_POUNDS:
                //calculation
                return siAmount * 2.205F;
            case WEIGHT_UNITS_KILOGRAMS:
                return siAmount;
            default:
                throw new InvalidCalculationException("The unit to provided is not applicable for a temperature conversion");
        }
    }
}
