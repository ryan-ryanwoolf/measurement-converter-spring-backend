package com.ryanwoolf.measurementconverter.utils;

import java.util.HashMap;
import java.util.Map;

public class GenericResponse {

    private final String message;
    private final Integer status;
    private final Boolean success;
    private final float answer;

    public GenericResponse(String message, Integer status,Boolean success,float answer) {
        this.message = message;
        this.status = status;
        this.success = success;
        this.answer = answer;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public float getAnswer() {
        return answer;
    }

}
