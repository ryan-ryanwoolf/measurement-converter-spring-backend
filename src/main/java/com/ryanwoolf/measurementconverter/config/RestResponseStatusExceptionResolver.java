package com.ryanwoolf.measurementconverter.config;

import com.ryanwoolf.measurementconverter.ExceptionHandling.ErrorDefinition;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidCalculationException;
import com.ryanwoolf.measurementconverter.ExceptionHandling.InvalidMeasurementException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestResponseStatusExceptionResolver  implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        System.out.println("in resolveException");
        ErrorDefinition errorMessage = new ErrorDefinition();
        errorMessage.setErrorMessage(exception.getMessage());
        ModelAndView model = new ModelAndView();
        model.setView(new MappingJackson2JsonView());
        model.addObject(errorMessage);
        if(exception instanceof InvalidMeasurementException){
            model.setStatus(HttpStatus.BAD_REQUEST);
        }
        else if(exception instanceof InvalidCalculationException){
            model.setStatus(HttpStatus.BAD_REQUEST);
        }
        else
        {
            model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return model;
    }
}


