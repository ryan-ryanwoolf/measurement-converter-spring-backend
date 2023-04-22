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
        String unitFrom = WeightService.WEIGHT_UNITS_KILOGRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_POUNDS;

        float expectedResult = 44.1f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_AnswerIsCreatedPoundsToKgs() throws Exception {
        float measurementAmount = 44.1f;
        String unitFrom = WeightService.WEIGHT_UNITS_POUNDS;
        String unitTo = WeightService.WEIGHT_UNITS_KILOGRAMS;

        float expectedResult = 20.0f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_AnswerIsCreatedGramsToKgs() throws Exception {
        float measurementAmount = 950f;
        String unitFrom = WeightService.WEIGHT_UNITS_GRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_KILOGRAMS;

        float expectedResult = .95f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_AnswerIsCreatedGramsToPounds() throws Exception {
        float measurementAmount = 950f;
        String unitFrom = WeightService.WEIGHT_UNITS_GRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_POUNDS;

        float expectedResult = 2.09f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_AnswerIsCreatedKgsToGrams() throws Exception {
        float measurementAmount = 950f;
        String unitFrom = WeightService.WEIGHT_UNITS_KILOGRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_GRAMS;

        float expectedResult = 950000f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_AnswerIsCreatedPoundsToGrams() throws Exception {
        float measurementAmount = 950f;
        String unitFrom = WeightService.WEIGHT_UNITS_POUNDS;
        String unitTo = WeightService.WEIGHT_UNITS_GRAMS;

        float expectedResult = 430839.03f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_ZeroConversionPounds() throws Exception {
        float measurementAmount = 0f;
        String unitFrom = WeightService.WEIGHT_UNITS_POUNDS;
        String unitTo = WeightService.WEIGHT_UNITS_KILOGRAMS;

        float expectedResult = 0f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_ZeroConversionGrams() throws Exception {
        float measurementAmount = 0f;
        String unitFrom = WeightService.WEIGHT_UNITS_GRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_KILOGRAMS;

        float expectedResult = 0f;
        assertEquals(expectedResult,weightService.convertMeasurement(measurementAmount,unitFrom,unitTo),.01);
    }

    @Test
    public void WeightService_ConvertWeight_CalculationError() throws Exception {
        float measurementAmount = 20.0f;
        String unitFrom = WeightService.WEIGHT_UNITS_GRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_POUNDS;
        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    weightService.convertMeasurement(measurementAmount,unitFrom,"invalid_unit");
                });

        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    weightService.convertMeasurement(measurementAmount,"invalid_unit",unitTo);
                });
    }
}