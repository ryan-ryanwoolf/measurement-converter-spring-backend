package com.ryanwoolf.measurementconverter.factories;

import com.ryanwoolf.measurementconverter.constants.MeasurementConstants;
import com.ryanwoolf.measurementconverter.services.ConversionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemperatureMeasurementTest {
    @Autowired
    ConversionService conversionService;

    @Test
    void shouldReturnCorrectImperialTemperature(){

        String measurementType = MeasurementConstants.TEMPERATURE;
        String measurementSystemFrom = MeasurementConstants.SYSTEM_METRIC;
        float celsiusAmount = 15.5f;
        float expectedAnswer = 59.9f;

        try {
            Assertions.assertEquals(conversionService.calculateConversion(celsiusAmount,measurementSystemFrom,measurementType),expectedAnswer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void shouldReturnCorrectMetricTemperature(){

        String measurementType = MeasurementConstants.TEMPERATURE;
        String measurementSystemFrom = MeasurementConstants.SYSTEM_IMPERIAL;
        float fahrenheitAmount = 59.9f;
        float expectedAnswer = 15.5f;

        try {
            Assertions.assertEquals(conversionService.calculateConversion(fahrenheitAmount,measurementSystemFrom,measurementType),expectedAnswer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}