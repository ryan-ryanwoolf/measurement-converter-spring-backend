package com.ryanwoolf.measurementconverter.factories;

import com.ryanwoolf.measurementconverter.constants.MeasurementConstants;
import com.ryanwoolf.measurementconverter.interfaces.Measurement;
import org.springframework.stereotype.Component;

@Component
public class KilogramPoundMeasurement implements Measurement {

    @Override
    public float convertMeasurement(float fromAmount, String measurementSystemFrom) {

        float convertedAmount = 0;
        if(measurementSystemFrom.equals(MeasurementConstants.SYSTEM_IMPERIAL)){
            convertedAmount = (float) (fromAmount / 2.205);
        }
        else if(measurementSystemFrom.equals(MeasurementConstants.SYSTEM_METRIC)){
            convertedAmount = (float) (fromAmount * 2.205);
        }

        return convertedAmount;

    }

}
