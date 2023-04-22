package com.ryanwoolf.measurementconverter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.services.DistanceService;
import com.ryanwoolf.measurementconverter.services.TemperatureService;
import com.ryanwoolf.measurementconverter.services.WeightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = ConversionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ConversionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TemperatureService temperatureService;

    @MockBean
    private DistanceService distanceService;

    @MockBean
    private WeightService weightService;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void ConversionController_ConvertTemperature_StatusIsOkAndJsonAsExpected() throws Exception {

        float measurementAmount = 30.0f;
        String unitFrom = TemperatureService.TEMPERATURE_UNITS_DEGREES_CELSIUS;
        String unitTo = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;

        float expectedResult = 86.0f;
        when(temperatureService.convertMeasurement(measurementAmount, unitFrom,unitTo)).thenReturn(expectedResult);
        mockMvc.perform(get("/api/convert-temperature")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"The measurement was calculated!\",\"status\": 200,success: true,\"answer\":86.0 }"));

    }

    @Test
    public void ConversionController_ConvertTemperature_InvalidCalculation() throws Exception {
        String unitFrom = "invalid_from_unit";
        String unitTo = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;

        float measurementAmount = 20.0f;
        when(temperatureService.convertMeasurement(measurementAmount, unitFrom,unitTo))
                .thenThrow(new InvalidCalculationException("Invalid from unit"));

        mockMvc.perform(get("/api/convert-temperature")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid from unit\"}}"));
    }

    @Test
    public void ConversionController_ConvertTemperature_InvalidMeasurement() throws Exception {
        String unitFrom = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;
        String unitTo = TemperatureService.TEMPERATURE_UNITS_FAHRENHEIT;

        float measurementAmount = -500.0f;
        when(temperatureService.convertMeasurement(measurementAmount, unitFrom,unitTo))
                .thenThrow(new InvalidMeasurementException("Invalid measurement"));

        mockMvc.perform(get("/api/convert-temperature")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid measurement\"}}"));
    }


    @Test
    public void ConversionController_ConvertDistance_StatusIsOkAndJsonAsExpected() throws Exception {

        float measurementAmount = 20.0f;
        String unitFrom = DistanceService.DISTANCE_UNITS_KILOMETERS;
        String unitTo = DistanceService.DISTANCE_UNITS_MILES;
        float expectedResult = 12.43f;
        when(distanceService.convertMeasurement(measurementAmount, unitFrom,unitTo)).thenReturn(expectedResult);
        mockMvc.perform(get("/api/convert-distance")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"The measurement was calculated!\",\"status\": 200,success: true,\"answer\":12.43 }"));

    }

    @Test
    public void ConversionController_ConvertDistance_InvalidCalculation() throws Exception {
        String unitFrom = DistanceService.DISTANCE_UNITS_MILES;
        float measurementAmount = 20.0f;
        when(distanceService.convertMeasurement(measurementAmount, unitFrom,"invalid_unit"))
                .thenThrow(new InvalidCalculationException("Invalid calculation ID"));

        mockMvc.perform(get("/api/convert-distance")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", "invalid_unit")
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid calculation ID\"}}"));
    }

    @Test
    public void ConversionController_ConvertDistance_InvalidMeasurement() throws Exception {
        String unitFrom = DistanceService.DISTANCE_UNITS_METERS;
        String unitTo = DistanceService.DISTANCE_UNITS_METERS;
        float measurementAmount = -500f;
        when(distanceService.convertMeasurement(measurementAmount, unitFrom,unitTo))
                .thenThrow(new InvalidMeasurementException("Invalid measurement"));

        mockMvc.perform(get("/api/convert-distance")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid measurement\"}}"));
    }

    @Test
    public void ConversionController_ConvertWeight_StatusIsOkAndJsonAsExpected() throws Exception {

        float measurementAmount = 20.0f;
        String unitFrom = WeightService.WEIGHT_UNITS_KILOGRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_POUNDS;

        float expectedResult = 44.1f;
        when(weightService.convertMeasurement(measurementAmount, unitFrom,unitTo)).thenReturn(expectedResult);
        mockMvc.perform(get("/api/convert-weight")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"The measurement was calculated!\",\"status\": 200,success: true,\"answer\":44.1 }"));

    }

    @Test
    public void ConversionController_ConvertWeight_InvalidCalculation() throws Exception {
        String unitFrom = WeightService.WEIGHT_UNITS_POUNDS;

        float measurementAmount = 20.0f;
        when(weightService.convertMeasurement(measurementAmount, unitFrom,"invalid_to_unit"))
                .thenThrow(new InvalidCalculationException("Invalid to unit"));

        mockMvc.perform(get("/api/convert-weight")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", "invalid_to_unit")
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid to unit\"}}"));
    }

    @Test
    public void ConversionController_ConvertWeight_InvalidMeasurement() throws Exception {
        String unitFrom = WeightService.WEIGHT_UNITS_KILOGRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_POUNDS;
        float measurementAmount = 20.0f;
        when(weightService.convertMeasurement(measurementAmount, unitFrom,unitTo))
                .thenThrow(new InvalidCalculationException("Invalid measurement"));

        mockMvc.perform(get("/api/convert-weight")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid measurement\"}}"));
    }

    @Test
    public void ConversionController_ConvertWeight_OtherError() throws Exception {
        String unitFrom = WeightService.WEIGHT_UNITS_GRAMS;
        String unitTo = WeightService.WEIGHT_UNITS_KILOGRAMS;
        float measurementAmount = 20.0f;
        when(weightService.convertMeasurement(measurementAmount, unitFrom,unitTo))
                .thenThrow(new NullPointerException());

        mockMvc.perform(get("/api/convert-weight")
                        .param("unitFrom", unitFrom)
                        .param("unitTo", unitTo)
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": null}}"));
    }
}