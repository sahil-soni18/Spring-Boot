package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class DependencyInjectionSupported {
    public String provider() {
        return "Details...";
    }

}
