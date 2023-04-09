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
class WeightServiceTest {

    @Autowired
    private WeightService weightService;

    @Test
    public void WeightService_ConvertWeight_AnswerIsCreatedKgToPounds() throws Exception {
        float measurementAmount = 20.0f;
        int calculationId = WeightService.WEIGHT_KILOGRAMS_TO_POUNDS;
        float expectedResult = 44.1f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,calculationId),.01);
    }

    @Test
    public void WeightService_ConvertWeight_AnswerIsCreatedPoundsToKgs() throws Exception {
        float measurementAmount = 44.1f;
        int calculationId = WeightService.WEIGHT_POUNDS_TO_KILOGRAMS;
        float expectedResult = 20.0f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,calculationId),.01);
    }

    @Test
    public void WeightService_ConvertWeight_CalculationError() throws Exception {
        float measurementAmount = 20.0f;
        int calculationId = -1;
        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    weightService.precheckValidations(measurementAmount,calculationId);
                });

        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    weightService.convertMeasurement(measurementAmount,calculationId);
                });
    }

    @Test
    public void WeightService_ConvertWeight_PrecheckValidationsPass() throws Exception {
        float measurementAmount = 12.43f;
        int calculationId = WeightService.WEIGHT_KILOGRAMS_TO_POUNDS;
        assertTrue(weightService.precheckValidations(measurementAmount,calculationId));
    }
}