package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class DistanceService  implements Measurement {

    public static final int DISTANCE_KILOMETERS_TO_MILES = 1;
    public static final int DISTANCE_MILES_TO_KILOMETERS = 2;


    @Override
    public float convertMeasurement(float fromAmount, int calculationId) throws InvalidCalculationException {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setMaximumFractionDigits(2);
        System.out.println("in convertMeasurement");
        switch (calculationId) {
            case DISTANCE_KILOMETERS_TO_MILES:
                float miles = fromAmount / 1.609F; // convert kilometers to miles
                return miles;
            case DISTANCE_MILES_TO_KILOMETERS:
                float kilometers = fromAmount * 1.609F; // convert miles to kilometers
                return kilometers;
            default:
                throw new InvalidCalculationException("The calculation provided is not applicable for a distance conversion");
                //throw invalid calculation type
                // code block
        }
    }

    @Override
    public boolean precheckValidations(float fromAmount, int calculationId) throws InvalidMeasurementException, InvalidCalculationException {
        List<Integer> validCalculations = Arrays.asList(DISTANCE_KILOMETERS_TO_MILES, DISTANCE_MILES_TO_KILOMETERS);
        if (!validCalculations.contains(calculationId)) {
            throw new InvalidCalculationException("The calculation provided is not applicable for a temperature conversion");
        }

        return true;
    }
}
