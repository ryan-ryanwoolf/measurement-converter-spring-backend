package com.ryanwoolf.measurementconverter.calculations;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TemperatureMeasurement implements Measurement {

    public static final int TEMPERATURE_CELSIUS_TO_FAHRENHEIT = 1;
    public static final int TEMPERATURE_FAHRENHEIT_TO_CELSIUS = 2;
    @Override
    public float convertMeasurement(float fromAmount, int calculationId) throws InvalidCalculationException {
        switch(calculationId) {
            case TEMPERATURE_CELSIUS_TO_FAHRENHEIT:
                int calculationPlus = 32;
                return (fromAmount * 9/5) + calculationPlus;
            case TEMPERATURE_FAHRENHEIT_TO_CELSIUS:
                int calculationMinus = 32;
                return (fromAmount - calculationMinus) * 5/9;
            default:
                throw new InvalidCalculationException("The calculation provided is not applicable for a temperature conversion");
                //throw invalid calculation type
                // code block
        }
    }

    @Override
    public boolean precheckValidations(float fromAmount, int calculationId) throws InvalidMeasurementException, InvalidCalculationException {
        List<Integer> validCalculations = Arrays.asList(TEMPERATURE_CELSIUS_TO_FAHRENHEIT,TEMPERATURE_FAHRENHEIT_TO_CELSIUS);
        if(!validCalculations.contains(calculationId)){
            throw new InvalidCalculationException("The calculation provided is not applicable for a temperature conversion");
        }

        switch(calculationId) {
            case TEMPERATURE_CELSIUS_TO_FAHRENHEIT:
                if(fromAmount < -273.15){
                    throw new InvalidMeasurementException("The amount provided is below the minimum possible temperature (-273.15 Degres Celsius), where all movement stops.");
                }
                break;
            case TEMPERATURE_FAHRENHEIT_TO_CELSIUS:
                if(fromAmount < -459.67){
                    throw new InvalidMeasurementException("The amount provided is below the minimum possible temperature (-459.67 Fahrenheit), where all movement stops.");
                }
                break;
        }

        return true;
    }

}
