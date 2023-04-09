package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemperatureServiceTest {

    @Autowired
    private TemperatureService temperatureService;

    @Test
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedCToF() throws Exception {
        float measurementAmount = 30.0f;
        int calculationId = TemperatureService.TEMPERATURE_CELSIUS_TO_FAHRENHEIT;
        float expectedResult = 86.0f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,calculationId),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedFToC() throws Exception {
        float measurementAmount = 86.0f;
        int calculationId = TemperatureService.TEMPERATURE_FAHRENHEIT_TO_CELSIUS;
        float expectedResult = 30.0f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,calculationId),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_CalculationError() throws Exception {
        float measurementAmount = 20.0f;
        int calculationId = -1;
        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    temperatureService.precheckValidations(measurementAmount,calculationId);
                });

        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    temperatureService.convertMeasurement(measurementAmount,calculationId);
                });
    }

    @Test
    public void TemperatureService_ConvertTemperature_MeasurementError() throws Exception {
        float measurementAmount = -500.0f;
        int calculationId = TemperatureService.TEMPERATURE_CELSIUS_TO_FAHRENHEIT;
        int finalCalculationId1 = calculationId;
        assertThrowsExactly(InvalidMeasurementException.class,
                ()->{
                    temperatureService.precheckValidations(measurementAmount, finalCalculationId1);
                });

        calculationId = TemperatureService.TEMPERATURE_FAHRENHEIT_TO_CELSIUS;

        int finalCalculationId = calculationId;
        assertThrowsExactly(InvalidMeasurementException.class,
                ()->{
                    temperatureService.precheckValidations(measurementAmount, finalCalculationId);
                });
    }

    @Test
    public void TemperatureService_ConvertTemperature_PrecheckValidationsPass() throws Exception {
        float measurementAmount = 12.43f;
        int calculationId = DistanceService.DISTANCE_MILES_TO_KILOMETERS;
        assertTrue(temperatureService.precheckValidations(measurementAmount,calculationId));
    }
}