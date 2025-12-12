package com.example.calculator;

import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTests {

    @Test
    void testAddition() throws Exception {
        CalculatorService service = new CalculatorService(null);
        String result = service.calculate("5+5");
        assertEquals("10", result);
    }

    @Test
    void testDivision() throws Exception {
        CalculatorService service = new CalculatorService(null);
        String result = service.calculate("20/4");
        assertEquals("5", result);
    }
}