package com.ryanwoolf.measurementconverter.factories;

import com.ryanwoolf.measurementconverter.constants.MeasurementConstants;
import com.ryanwoolf.measurementconverter.services.ConversionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KilometerMilesMeasurementTest {

    @Autowired
    ConversionService conversionService;

    @Test
    void shouldReturnCorrectImperialMiles(){

        String measurementType = MeasurementConstants.KILOMETER_MILES;
        String measurementSystemFrom = MeasurementConstants.SYSTEM_METRIC;
        float kilometerAmount = 8.046736f;
        float expectedAnswer = 5f;
        try {
            Assertions.assertEquals(conversionService.calculateConversion(kilometerAmount,measurementSystemFrom,measurementType),expectedAnswer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void shouldReturnCorrectMetricKilometers(){

        String measurementType = MeasurementConstants.KILOMETER_MILES;
        String measurementSystemFrom = MeasurementConstants.SYSTEM_IMPERIAL;

        float milesAmount = 5f;
        float expectedAnswer = 8.046736f;

        try {
            Assertions.assertEquals(conversionService.calculateConversion(milesAmount,measurementSystemFrom,measurementType),expectedAnswer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}