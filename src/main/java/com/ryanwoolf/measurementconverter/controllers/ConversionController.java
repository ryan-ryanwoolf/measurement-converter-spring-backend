package com.ryanwoolf.measurementconverter.controllers;

import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import com.ryanwoolf.measurementconverter.services.ConversionService;
import com.ryanwoolf.measurementconverter.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConversionController {

    @Autowired
    ConversionService conversionService;

    /*
    Ryan Woolf
    This is the endpoint that calculates the conversion from metric to imperial and vice versa
     */
    @CrossOrigin( origins = {"http://localhost:4200", "https://www.ryanwoolftechnicalassessment"} )
    @GetMapping("/measure-units")
    public ResponseEntity<GenericResponse> example(@RequestParam("measurementType") String measurementType,
                                                   @RequestParam("measurementSystemFrom") String measurementSystemFrom,
                                                   @RequestParam("measurementAmount") float measurementAmount){
        try{

            float getAnswer = conversionService.calculateConversion(measurementAmount,measurementSystemFrom,measurementType);
            GenericResponse response = new GenericResponse("The measurement was calculated!",200,true, getAnswer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e)
        {
            GenericResponse errorResponse = new GenericResponse("Failed",500,false,0);
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }


    }

}
