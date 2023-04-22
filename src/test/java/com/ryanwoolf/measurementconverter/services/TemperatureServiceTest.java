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
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedCelsiusToFahrenheit() throws Exception {
        float measurementAmount = 30.0f;
        String fromUnit = TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS;
        String toUnit = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;
        float expectedResult = 86.0f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,fromUnit,toUnit),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedFahrenheitToCelsius() throws Exception {
        float measurementAmount = 86.0f;
        String fromUnit = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;
        String toUnit = TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS;
        float expectedResult = 30.0f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,fromUnit,toUnit),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedKelvinToCelsius() throws Exception {
        float measurementAmount = 298.0f;
        String fromUnit = TemperatureService.TEMPERATURE_UNITS_KELVINS;
        String toUnit = TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS;
        float expectedResult = 24.85f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,fromUnit,toUnit),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedKelvinToFahrenheit() throws Exception {
        float measurementAmount = 298.0f;
        String fromUnit = TemperatureService.TEMPERATURE_UNITS_KELVINS;
        String toUnit = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;
        float expectedResult = 76.73f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,fromUnit,toUnit),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedCelsiusToKelvin() throws Exception {
        float measurementAmount = 35.0f;
        String fromUnit = TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS;
        String toUnit = TemperatureService.TEMPERATURE_UNITS_KELVINS;
        float expectedResult = 308.15f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,fromUnit,toUnit),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_AnswerIsCreatedFahrenheitToKelvin() throws Exception {
        float measurementAmount = 68.0f;
        String fromUnit = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;
        String toUnit = TemperatureService.TEMPERATURE_UNITS_KELVINS;
        float expectedResult = 293.15f;
        assertEquals(expectedResult,temperatureService.convertMeasurement(measurementAmount,fromUnit,toUnit),.01);
    }

    @Test
    public void TemperatureService_ConvertTemperature_CalculationError() throws Exception {
        float measurementAmount = 20.0f;
        int calculationId = -1;
        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    temperatureService.convertMeasurement(measurementAmount,"incorrectunit",TemperatureService.TEMPERATURE_UNITS_KELVINS);
                });

        assertThrowsExactly(InvalidCalculationException.class,
                ()->{
                    temperatureService.convertMeasurement(measurementAmount,TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS,"incorrectunit");
                });
    }

    @Test
    public void TemperatureService_ConvertTemperature_MeasurementError() throws Exception {
        float measurementAmount = -500.0f;
        String fromUnit = TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS;
        String toUnit = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;
        assertThrowsExactly(InvalidMeasurementException.class,
                ()->{
                    temperatureService.convertMeasurement(measurementAmount, fromUnit,toUnit);
                });
        String toUnit2 = TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS;
        String fromUnit2 = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;

        assertThrowsExactly(InvalidMeasurementException.class,
                ()->{
                    temperatureService.convertMeasurement(measurementAmount, fromUnit2,toUnit2);
                });
    }

}