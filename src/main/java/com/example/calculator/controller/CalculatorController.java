package com.example.calculator.controller;

import com.example.calculator.service.CalculatorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    private final CalculatorService service;

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") int page, 
                      @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Calculation> historyPage = service.getHistory(pageable);
        
        model.addAttribute("history", historyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", historyPage.getTotalPages());
        model.addAttribute("totalItems", historyPage.getTotalElements());
        
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam String expression, Model model) {
        try {
            String result = service.calculate(expression);
            model.addAttribute("answer", result);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("answer", "Error: " + e.getMessage());
            model.addAttribute("error", true);
        }

        // Refresh history after calculation
        Pageable pageable = PageRequest.of(0, 10);
        Page<Calculation> historyPage = service.getHistory(pageable);
        model.addAttribute("history", historyPage.getContent());
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", historyPage.getTotalPages());
        model.addAttribute("totalItems", historyPage.getTotalElements());
        
        return "index";
    }
}