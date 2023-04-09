package com.ryanwoolf.measurementconverter.services;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DistanceServiceTest {

    @Autowired
    private DistanceService distanceService;

    @Test
    public void DistanceService_ConvertDistance_AnswerIsCreatedKMToMiles() throws Exception {
        float measurementAmount = 20.0f;
        int calculationId = DistanceService.DISTANCE_KILOMETERS_TO_MILES;
        float expectedResult = 12.43f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,calculationId),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_AnswerIsCreatedMilesToKM() throws Exception {
        float measurementAmount = 12.43f;
        int calculationId = DistanceService.DISTANCE_MILES_TO_KILOMETERS;
        float expectedResult = 20.0f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,calculationId),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_PrecheckValidationsPass() throws Exception {
        float measurementAmount = 12.43f;
        int calculationId = DistanceService.DISTANCE_MILES_TO_KILOMETERS;
        assertTrue(distanceService.precheckValidations(measurementAmount,calculationId));
    }

    @Test
    public void DistanceService_ConvertDistance_CalculationError() throws Exception {
        float measurementAmount = 20.0f;
        int calculationId = -1;
        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    distanceService.precheckValidations(measurementAmount,calculationId);
                });

        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    distanceService.convertMeasurement(measurementAmount,calculationId);
                });
    }
}