package com.ryanwoolf.measurementconverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeasurementConverterApplicationTest {

    @Autowired
    MeasurementConverterApplication measurementConverterApplication;
    @Test
    public void testMain() {
        measurementConverterApplication.main(new String[] {});
        assertTrue(true);
    }
}