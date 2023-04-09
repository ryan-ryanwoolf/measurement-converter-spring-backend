package com.ryanwoolf.measurementconverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServletInitializerTest {

    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    ServletInitializer servletInitializer;

    @Test
    public void testConfigure() {
        ServletInitializer servletInitializer = new ServletInitializer();

        Mockito.when(springApplicationBuilder.sources(MeasurementConverterApplication.class)).thenReturn(springApplicationBuilder);

        SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

        Mockito.verify(springApplicationBuilder).sources(MeasurementConverterApplication.class);

        assertEquals(springApplicationBuilder, result);
    }

}