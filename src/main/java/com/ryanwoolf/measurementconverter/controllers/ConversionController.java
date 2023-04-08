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

    @CrossOrigin( origins = {"http://localhost:4200"} ) //Dev
//    @CrossOrigin( origins = {"https://www.ryanwoolftechnicalassessment.co.za"} ) //Prod
    @GetMapping("/convert-temperature")
    public ResponseEntity<GenericResponse> convertTemperature(
                                                   @RequestParam("calculationId") int calculationId,
                                                   @RequestParam("measurementAmount") float measurementAmount) throws InvalidCalculationException, InvalidMeasurementException {
            float getAnswer = temperatureService.calculateTemperature(measurementAmount,calculationId);
            GenericResponse response = new GenericResponse("The measurement was calculated!",200,true, getAnswer);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @CrossOrigin( origins = {"http://localhost:4200"} ) //Dev
//    @CrossOrigin( origins = {"https://www.ryanwoolftechnicalassessment.co.za"} ) //Prod
    @GetMapping("/convert-distance")
    public ResponseEntity<GenericResponse> convertDistance(
            @RequestParam("calculationId") int calculationId,
            @RequestParam("measurementAmount") float measurementAmount) throws InvalidCalculationException, InvalidMeasurementException {
        float getAnswer = distanceService.calculateDistance(measurementAmount,calculationId);
        GenericResponse response = new GenericResponse("The measurement was calculated!",200,true, getAnswer);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @CrossOrigin( origins = {"http://localhost:4200"} ) //Dev
//    @CrossOrigin( origins = {"https://www.ryanwoolftechnicalassessment.co.za"} ) //Prod
    @GetMapping("/convert-weight")
    public ResponseEntity<GenericResponse> convertWeight(
            @RequestParam("calculationId") int calculationId,
            @RequestParam("measurementAmount") float measurementAmount) throws InvalidCalculationException, InvalidMeasurementException {
        float getAnswer = weightService.calculateWeight(measurementAmount,calculationId);
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
