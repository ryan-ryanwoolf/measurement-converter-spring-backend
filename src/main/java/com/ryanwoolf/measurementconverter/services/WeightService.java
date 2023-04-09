package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WeightService  implements Measurement {

    public static final int WEIGHT_KILOGRAMS_TO_POUNDS = 1;
    public static final int WEIGHT_POUNDS_TO_KILOGRAMS = 2;
    @Override
    public float convertMeasurement(float fromAmount, int calculationId) throws InvalidCalculationException {
        switch(calculationId) {
            case WEIGHT_KILOGRAMS_TO_POUNDS:
                return (fromAmount * 2.205F);
            case WEIGHT_POUNDS_TO_KILOGRAMS:
                return (fromAmount / 2.205F);
            default:
                throw new InvalidCalculationException("The calculation provided is not applicable for a weight conversion");
                //throw invalid calculation type
                // code block
        }
    }

    @Override
    public boolean precheckValidations(float fromAmount, int calculationId) throws InvalidMeasurementException, InvalidCalculationException {
        List<Integer> validCalculations = Arrays.asList(WEIGHT_KILOGRAMS_TO_POUNDS,WEIGHT_POUNDS_TO_KILOGRAMS);
        if(!validCalculations.contains(calculationId)){
            throw new InvalidCalculationException("The calculation provided is not applicable for a weight conversion");
        }

        return true;
    }
}
