package com.ryanwoolf.measurementconverter.factories;

import com.ryanwoolf.measurementconverter.constants.MeasurementConstants;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Component;


import java.util.Objects;
@Component
public class TemperatureMeasurement implements Measurement {

    @Override
    public float convertMeasurement(float fromAmount, String measurementSystemFrom) {

        float convertedAmount = 0;
        if(measurementSystemFrom.equals(MeasurementConstants.SYSTEM_IMPERIAL)){
            int calculationMinus = 32;
            convertedAmount = (fromAmount - calculationMinus) * 5/9;;
        }
        else if(measurementSystemFrom.equals(MeasurementConstants.SYSTEM_METRIC)){
            convertedAmount =  (fromAmount * 9/5) + 32;
        }

        return convertedAmount;

    }

}
