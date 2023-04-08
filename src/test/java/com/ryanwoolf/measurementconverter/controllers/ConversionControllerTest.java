package com.ryanwoolf.measurementconverter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.calculations.DistanceMeasurement;
import com.ryanwoolf.measurementconverter.calculations.TemperatureMeasurement;
import com.ryanwoolf.measurementconverter.calculations.WeightMeasurement;
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
        int calculationId = TemperatureMeasurement.TEMPERATURE_CELSIUS_TO_FAHRENHEIT;
        float expectedResult = 86.0f;
        when(temperatureService.calculateTemperature(measurementAmount, calculationId)).thenReturn(expectedResult);
        mockMvc.perform(get("/api/convert-temperature")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"The measurement was calculated!\",\"status\": 200,success: true,\"answer\":86.0 }"));

    }

    @Test
    public void ConversionController_ConvertTemperature_InvalidCalculation() throws Exception {
        int calculationId = -1; // an invalid calculation ID
        float measurementAmount = 20.0f;
        when(temperatureService.calculateTemperature(measurementAmount, calculationId))
                .thenThrow(new InvalidCalculationException("Invalid calculation ID"));

        mockMvc.perform(get("/api/convert-temperature")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid calculation ID\"}}"));
    }

    @Test
    public void ConversionController_ConvertTemperature_InvalidMeasurement() throws Exception {
        int calculationId = -1; // an invalid calculation ID
        float measurementAmount = 20.0f;
        when(temperatureService.calculateTemperature(measurementAmount, calculationId))
                .thenThrow(new InvalidMeasurementException("Invalid measurement"));

        mockMvc.perform(get("/api/convert-temperature")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid measurement\"}}"));
    }


    @Test
    public void ConversionController_ConvertDistance_StatusIsOkAndJsonAsExpected() throws Exception {

        float measurementAmount = 20.0f;
        int calculationId = DistanceMeasurement.DISTANCE_KILOMETERS_TO_MILES;
        float expectedResult = 12.43f;
        when(distanceService.calculateDistance(measurementAmount, calculationId)).thenReturn(expectedResult);
        mockMvc.perform(get("/api/convert-distance")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"The measurement was calculated!\",\"status\": 200,success: true,\"answer\":12.43 }"));

    }

    @Test
    public void ConversionController_ConvertDistance_InvalidCalculation() throws Exception {
        int calculationId = -1; // an invalid calculation ID
        float measurementAmount = 20.0f;
        when(distanceService.calculateDistance(measurementAmount, calculationId))
                .thenThrow(new InvalidCalculationException("Invalid calculation ID"));

        mockMvc.perform(get("/api/convert-distance")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid calculation ID\"}}"));
    }

    @Test
    public void ConversionController_ConvertDistance_InvalidMeasurement() throws Exception {
        int calculationId = -1; // an invalid calculation ID
        float measurementAmount = 20.0f;
        when(distanceService.calculateDistance(measurementAmount, calculationId))
                .thenThrow(new InvalidMeasurementException("Invalid measurement"));

        mockMvc.perform(get("/api/convert-distance")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid measurement\"}}"));
    }

    @Test
    public void ConversionController_ConvertWeight_StatusIsOkAndJsonAsExpected() throws Exception {

        float measurementAmount = 20.0f;
        int calculationId = WeightMeasurement.WEIGHT_KILOGRAMS_TO_POUNDS;
        float expectedResult = 44.1f;
        when(weightService.calculateWeight(measurementAmount, calculationId)).thenReturn(expectedResult);
        mockMvc.perform(get("/api/convert-weight")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"The measurement was calculated!\",\"status\": 200,success: true,\"answer\":44.1 }"));

    }

    @Test
    public void ConversionController_ConvertWeight_InvalidCalculation() throws Exception {
        int calculationId = -1; // an invalid calculation ID
        float measurementAmount = 20.0f;
        when(weightService.calculateWeight(measurementAmount, calculationId))
                .thenThrow(new InvalidCalculationException("Invalid calculation ID"));

        mockMvc.perform(get("/api/convert-weight")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid calculation ID\"}}"));
    }

    @Test
    public void ConversionController_ConvertWeight_InvalidMeasurement() throws Exception {
        int calculationId = -1; // an invalid calculation ID
        float measurementAmount = 20.0f;
        when(weightService.calculateWeight(measurementAmount, calculationId))
                .thenThrow(new InvalidCalculationException("Invalid measurement"));

        mockMvc.perform(get("/api/convert-weight")
                        .param("calculationId", String.valueOf(calculationId))
                        .param("measurementAmount", String.valueOf(measurementAmount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorDefinition\": {\"errorMessage\": \"Invalid measurement\"}}"));
    }
}