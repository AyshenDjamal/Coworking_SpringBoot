package com.example.springapi.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page");
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Redirecting to Admin Dashboard");
    }

    @GetMapping("/customer/dashboard")
    public ResponseEntity<String> customerDashboard() {
        return ResponseEntity.ok("Redirecting to Customer Dashboard");
    }
}

