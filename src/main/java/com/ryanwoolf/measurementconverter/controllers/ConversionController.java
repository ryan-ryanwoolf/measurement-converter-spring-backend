package com.ryanwoolf.measurementconverter.controllers;

import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import com.ryanwoolf.measurementconverter.services.DistanceService;
import com.ryanwoolf.measurementconverter.services.TemperatureService;
import com.ryanwoolf.measurementconverter.services.WeightService;
import com.ryanwoolf.measurementconverter.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConversionController {

    
    @Autowired
    TemperatureService temperatureService;

    @Autowired
    DistanceService distanceService;

    @Autowired
    WeightService weightService;
    /*
    Ryan Woolf
    This is the endpoint that calculates the conversion from metric to imperial and vice versa
     */

//    @CrossOrigin( origins = {"http://localhost:4200"} ) //Dev
    @CrossOrigin( origins = {"https://master.d3gm55w78wva9q.amplifyapp.com"} ) //Prod
    @GetMapping("/convert-temperature")
    public ResponseEntity<GenericResponse> convertTemperature(
                                                   @RequestParam("unitFrom") String unitFrom,
                                                   @RequestParam("unitTo") String unitTo,
                                                   @RequestParam("measurementAmount") float measurementAmount) throws InvalidCalculationException, InvalidMeasurementException {
//        temperatureService.precheckValidations(measurementAmount,calculationId);
        float getAnswer = temperatureService.convertMeasurement(measurementAmount,unitFrom,unitTo);
            GenericResponse response = new GenericResponse("The measurement was calculated!",200,true, getAnswer);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @CrossOrigin( origins = {"http://localhost:4200"} ) //Dev
    @CrossOrigin( origins = {"https://master.d3gm55w78wva9q.amplifyapp.com"} ) //Prod
    @GetMapping("/convert-distance")
    public ResponseEntity<GenericResponse> convertDistance(
            @RequestParam("unitFrom") String unitFrom,
            @RequestParam("unitTo") String unitTo,
            @RequestParam("measurementAmount") float measurementAmount) throws InvalidCalculationException, InvalidMeasurementException {
        float getAnswer = distanceService.convertMeasurement(measurementAmount,unitFrom,unitTo);
        GenericResponse response = new GenericResponse("The measurement was calculated!",200,true, getAnswer);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @CrossOrigin( origins = {"http://localhost:4200"} ) //Dev
    @CrossOrigin( origins = {"https://master.d3gm55w78wva9q.amplifyapp.com"} ) //Prod
    @GetMapping("/convert-weight")
    public ResponseEntity<GenericResponse> convertWeight(
            @RequestParam("unitFrom") String unitFrom,
            @RequestParam("unitTo") String unitTo,
            @RequestParam("measurementAmount") float measurementAmount) throws InvalidCalculationException, InvalidMeasurementException {
        float getAnswer = weightService.convertMeasurement(measurementAmount,unitFrom,unitTo);
        GenericResponse response = new GenericResponse("The measurement was calculated!",200,true, getAnswer);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @CrossOrigin( origins = {"http://localhost:4200", "https://www.ryanwoolftechnicalassessment.co.za"} )
//    @GetMapping("/measure-units")
//    public ResponseEntity<GenericResponse> measureUnits(@RequestParam("measurementType") String measurementType,
//                                                        @RequestParam("measurementSystemFrom") String measurementSystemFrom,
//                                                        @RequestParam("measurementAmount") float measurementAmount) {
//
//        System.out.println("in measureUnits");
//        float getAnswer = conversionService.calculateConversion(measurementAmount,measurementSystemFrom,measurementType);
//        GenericResponse response = new GenericResponse("The measurement was calculated!",200,true, getAnswer);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//
//    }
}
