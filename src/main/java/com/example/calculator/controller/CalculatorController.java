package com.example.calculator.controller;

import com.example.calculator.service.CalculatorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    private final CalculatorService service;

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<?> history = service.getHistory(pageable);

        model.addAttribute("history", history.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", history.getTotalPages());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam String expression, Model model) {

        try {
            String answer = service.calculate(expression);
            model.addAttribute("answer", answer);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("history", service.getHistory(pageable).getContent());
        return "index";
    }
}