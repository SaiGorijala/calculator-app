package com.example.calculator.service;

import com.example.calculator.model.Calculation;
import com.example.calculator.repository.CalculationRepository;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final CalculationRepository repo;

    public CalculatorService(CalculationRepository repo) {
        this.repo = repo;
    }

    public String calculate(String expression) throws Exception {

        // Validate expression
        if (!expression.matches("[0-9+\\-*/().\\s]+")) {
            throw new IllegalArgumentException("Invalid characters in expression");
        }

        // Parse & evaluate
        try {
            Expression exp = new ExpressionBuilder(expression).build();
            double resultValue = exp.evaluate();
            String result = String.valueOf(resultValue);

            // Save history
            repo.save(new Calculation(expression, result));

            return result;

        } catch (Exception e) {
            throw new Exception("Invalid expression: " + e.getMessage());
        }
    }

    public Page<Calculation> getHistory(Pageable pageable) {
        return repo.findAllByOrderByTimestampDesc(pageable);
    }
}