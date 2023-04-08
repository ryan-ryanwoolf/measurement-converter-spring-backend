package com.ryanwoolf.measurementconverter.factories;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.constants.MeasurementConstants;
import com.ryanwoolf.measurementconverter.services.ConversionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KilogramPoundMeasurementTest {


    @Autowired
    ConversionService conversionService;

    @Test
    void shouldReturnCorrectImperialPounds() throws InvalidCalculationException, InvalidMeasurementException {

        String measurementType = MeasurementConstants.KILOGRAM_POUNDS;
        String measurementSystemFrom = MeasurementConstants.SYSTEM_METRIC;
        float kilogramAmount = 3.1746066f;
        float expectedAnswer = 7.000007518f;

        Assertions.assertEquals(conversionService.calculateConversion(kilogramAmount,measurementSystemFrom,measurementType),expectedAnswer);
    }

    @Test
    void shouldReturnCorrectMetricKilograms() throws InvalidCalculationException, InvalidMeasurementException {

        String measurementType = MeasurementConstants.KILOGRAM_POUNDS;
        String measurementSystemFrom = MeasurementConstants.SYSTEM_IMPERIAL;
        float poundAmount = 7.000007518f;
        float expectedAnswer = 3.1746066f;

        Assertions.assertEquals(conversionService.calculateConversion(poundAmount,measurementSystemFrom,measurementType),expectedAnswer);
    }

}