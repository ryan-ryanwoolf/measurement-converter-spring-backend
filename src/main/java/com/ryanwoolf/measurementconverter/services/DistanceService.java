package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.calculations.DistanceMeasurement;
import com.ryanwoolf.measurementconverter.calculations.TemperatureMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    @Autowired
    DistanceMeasurement distanceMeasurement;

    public float calculateDistance(float measurementAmount,int calculationId) throws InvalidCalculationException, InvalidMeasurementException {
        distanceMeasurement.precheckValidations(measurementAmount,calculationId);
        return distanceMeasurement.convertMeasurement(measurementAmount,calculationId);
    }
}
