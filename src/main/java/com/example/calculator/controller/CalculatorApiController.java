package com.example.calculator.controller;

import com.example.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculatorApiController {

    private final CalculatorService service;

    public CalculatorApiController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam String expression) throws Exception {
        return service.calculate(expression);
    }
}