package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.calculations.TemperatureMeasurement;
import com.ryanwoolf.measurementconverter.calculations.WeightMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeightService {

    @Autowired
    WeightMeasurement weightMeasurement;

    public float calculateWeight(float measurementAmount,int calculationId) throws InvalidCalculationException, InvalidMeasurementException {
        weightMeasurement.precheckValidations(measurementAmount,calculationId);
        return weightMeasurement.convertMeasurement(measurementAmount,calculationId);
    }
}
