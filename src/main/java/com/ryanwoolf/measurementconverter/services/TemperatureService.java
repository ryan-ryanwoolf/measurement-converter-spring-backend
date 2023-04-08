package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.calculations.TemperatureMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {

    @Autowired
    TemperatureMeasurement temperatureMeasurement;

    public float calculateTemperature(float measurementAmount,int calculationId) throws InvalidCalculationException, InvalidMeasurementException {
        temperatureMeasurement.precheckValidations(measurementAmount,calculationId);
        return temperatureMeasurement.convertMeasurement(measurementAmount,calculationId);
    }
}
