package com.example.calculator.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expression;
    private String result;

    private LocalDateTime timestamp = LocalDateTime.now();

    public Calculation() {}

    public Calculation(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public Long getId() { return id; }
    public String getExpression() { return expression; }
    public String getResult() { return result; }
    public LocalDateTime getTimestamp() { return timestamp; }
}