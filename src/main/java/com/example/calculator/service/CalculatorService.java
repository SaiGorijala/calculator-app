package com.example.calculator.service;

import com.example.calculator.model.Calculation;
import com.example.calculator.repository.CalculationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Service
public class CalculatorService {
    private final CalculationRepository repo;
    private final ScriptEngine engine;

    public CalculatorService(CalculationRepository repo) {
        this.repo = repo;
        this.engine = new ScriptEngineManager().getEngineByName("JavaScript");
    }

    public String calculate(String expression) throws Exception {
        // Input validation to prevent code injection
        if (!expression.matches("[0-9+\\-*/().\\s]+")) {
            throw new IllegalArgumentException("Invalid characters in expression");
        }
        
        Object evalResult = engine.eval(expression);
        String result = evalResult.toString();
        
        // Save to DB
        repo.save(new Calculation(expression, result));
        
        return result;
    }

    public Page<Calculation> getHistory(Pageable pageable) {
        return repo.findAllByOrderByTimestampDesc(pageable);
    }
}