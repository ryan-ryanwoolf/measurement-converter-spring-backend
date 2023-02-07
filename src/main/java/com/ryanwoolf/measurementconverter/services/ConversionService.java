package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.constants.MeasurementConstants;
import com.ryanwoolf.measurementconverter.factories.KilogramPoundMeasurement;
import com.ryanwoolf.measurementconverter.factories.KilometerMilesMeasurement;
import com.ryanwoolf.measurementconverter.factories.TemperatureMeasurement;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ConversionService {

    @Autowired
    TemperatureMeasurement temperatureMeasurement;

    @Autowired
    KilometerMilesMeasurement kilometerMilesMeasurement;

    @Autowired
    KilogramPoundMeasurement kilogramPoundMeasurement;

    private static final Map<String, Measurement> handler = new HashMap<String, Measurement>();

    /*
   Ryan Woolf
   Creates a map of conversion types so that it uses the correct class type.
    */
    @PostConstruct
    private Map<String, Measurement> getObject() {
        handler.put(MeasurementConstants.TEMPERATURE, temperatureMeasurement);
        handler.put(MeasurementConstants.KILOMETER_MILES, kilometerMilesMeasurement);
        handler.put(MeasurementConstants.KILOGRAM_POUNDS, kilogramPoundMeasurement);
        return handler;
    }

    /*
    Ryan Woolf
    works out the type of measurement and calculates the conversion and returns it as a float.
     */
    public float calculateConversion(float measurementAmount,String measurementSystemFrom,String measurementType) throws Exception {
        Measurement measurement = Optional.ofNullable(handler.get(measurementType)).orElseThrow(() -> new IllegalArgumentException("Invalid measurement"));
        return measurement.convertMeasurement(measurementAmount,measurementSystemFrom);
    }
}
