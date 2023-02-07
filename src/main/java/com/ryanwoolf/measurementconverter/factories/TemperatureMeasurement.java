package com.ryanwoolf.measurementconverter.factories;

import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Component;


import java.util.Objects;
@Component
public class TemperatureMeasurement implements Measurement {

    @Override
    public float convertMeasurement(float fromAmount, String measurementSystemFrom) {

        float convertedAmount = 0;
        if(measurementSystemFrom.equals( "imperial")){
            int calculationMinus = 32;
            convertedAmount = (fromAmount - calculationMinus) * 5/9;;
        }
        else if(measurementSystemFrom.equals( "metric")){
            convertedAmount =  (fromAmount * 9/5) + 32;
        }

        return convertedAmount;

    }

}
