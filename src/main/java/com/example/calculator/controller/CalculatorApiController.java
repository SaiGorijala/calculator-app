package com.example.calculator.controller;

import com.example.calculator.model.Calculation;
import com.example.calculator.service.CalculatorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalculatorApiController {
    private final CalculatorService service;

    public CalculatorApiController(CalculatorService service) {
        this.service = service;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, String>> calculateApi(@RequestBody Map<String, String> request) {
        try {
            String expression = request.get("expression");
            String result = service.calculate(expression);
            Map<String, String> response = new HashMap<>();
            response.put("expression", expression);
            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/history")
    public Page<Calculation> getHistoryApi(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getHistory(pageable);
    }
}