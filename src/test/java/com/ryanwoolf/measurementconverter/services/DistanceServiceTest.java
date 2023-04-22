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
        String unitFrom = DistanceService.DISTANCE_UNITS_KILOMETERS;
        String unitTo = DistanceService.DISTANCE_UNITS_MILES;
        float expectedResult = 12.43f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_AnswerIsCreatedMilesToKM() throws Exception {
        float measurementAmount = 12.43f;
        String unitFrom = DistanceService.DISTANCE_UNITS_MILES;
        String unitTo = DistanceService.DISTANCE_UNITS_KILOMETERS;
        float expectedResult = 20.0f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_AnswerIsCreatedMilesToMetres() throws Exception {
        float measurementAmount = 12.4300f;
        String unitFrom = DistanceService.DISTANCE_UNITS_MILES;
        String unitTo = DistanceService.DISTANCE_UNITS_METERS;
        float expectedResult = 19999.87f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_AnswerIsCreatedKilometersToMetres() throws Exception {
        float measurementAmount = 12.4300f;
        String unitFrom = DistanceService.DISTANCE_UNITS_KILOMETERS;
        String unitTo = DistanceService.DISTANCE_UNITS_METERS;
        float expectedResult = 12430f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_AnswerIsCreatedMetersToMiles() throws Exception {
        float measurementAmount = 1500f;
        String unitFrom = DistanceService.DISTANCE_UNITS_METERS;
        String unitTo = DistanceService.DISTANCE_UNITS_MILES;
        float expectedResult = 0.932f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_AnswerIsCreatedMetersToKilometers() throws Exception {
        float measurementAmount = 1500f;
        String unitFrom = DistanceService.DISTANCE_UNITS_METERS;
        String unitTo = DistanceService.DISTANCE_UNITS_KILOMETERS;
        float expectedResult = 1.5f;
        assertEquals(expectedResult,distanceService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void DistanceService_ConvertDistance_CalculationError() throws Exception {
        float measurementAmount = 20.0f;
        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    distanceService.convertMeasurement(measurementAmount,"invalid_from_unit",DistanceService.DISTANCE_UNITS_KILOMETERS);
                });

        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    distanceService.convertMeasurement(measurementAmount,DistanceService.DISTANCE_UNITS_KILOMETERS,"invalid_from_unit");
                });
    }
}