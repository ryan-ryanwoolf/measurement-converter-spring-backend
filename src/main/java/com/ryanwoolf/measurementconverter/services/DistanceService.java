package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class DistanceService {
    public static final String DISTANCE_UNITS_METERS = "meters";
    public static final String DISTANCE_UNITS_KILOMETERS = "kilometers";
    public static final String DISTANCE_UNITS_MILES = "miles";


    public float convertMeasurement(float fromAmount, String unitFrom,String unitTo) throws InvalidCalculationException, InvalidMeasurementException {
        float convertedToSIUnit = convertToSIUnit(fromAmount,unitFrom);
        System.out.println("convertedToSIUnit:"+convertedToSIUnit);
        System.out.println("convertedFromSIUnit:"+convertFromSIUnit(convertedToSIUnit,unitTo));
        return convertFromSIUnit(convertedToSIUnit,unitTo);
    }

    public float convertToSIUnit(float fromAmount,String unitFrom) throws InvalidCalculationException {
        switch(unitFrom) {
            case DISTANCE_UNITS_KILOMETERS:
                //calculation
                    return fromAmount * 1000;
            case DISTANCE_UNITS_MILES:
                //calculation
                return fromAmount * 1609;
            case DISTANCE_UNITS_METERS:
                return fromAmount;
            default:
                throw new InvalidCalculationException("The unit from provided is not applicable for a temperature conversion");
        }
    }

    public float convertFromSIUnit(float siAmount,String unitTo) throws InvalidCalculationException {
        switch(unitTo) {
            case DISTANCE_UNITS_KILOMETERS:
                //calculation
                return siAmount / 1000;
            case DISTANCE_UNITS_MILES:
                //calculation
                return siAmount / 1609;
            case DISTANCE_UNITS_METERS:
                return siAmount;
            default:
                throw new InvalidCalculationException("The unit to provided is not applicable for a temperature conversion");
        }
    }

}
