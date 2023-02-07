package com.ryanwoolf.measurementconverter.factories;

import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Component;

@Component
public class KilometerMilesMeasurement implements Measurement {

    @Override
    public float convertMeasurement(float fromAmount, String measurementSystemFrom) {

        float convertedAmount = 0;
        if(measurementSystemFrom.equals( "imperial")){
            convertedAmount = (float) (fromAmount * 0.62137);
        }
        else if(measurementSystemFrom.equals( "metric")){
            int calculationMinus = 32;
            convertedAmount = (float) (fromAmount / 0.62137);
        }

        return convertedAmount;

    }

}
