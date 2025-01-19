package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DependencyInjection {

    @Autowired
    private DependencyInjectionSupported supporter;   // Field Injection

    @GetMapping("/dependency-injection")
    public String dependencyInjection() {
        return supporter.provider();
    }
}
