package com.ryanwoolf.measurementconverter.interfaces;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;

public interface Measurement {
    float convertMeasurement(float fromAmount, int calculationId) throws InvalidCalculationException;

    boolean precheckValidations(float fromAmount, int calculationId) throws InvalidMeasurementException, InvalidCalculationException;

}
