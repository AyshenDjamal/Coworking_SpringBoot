package com.example.springapi.controller;


import com.example.springapi.entity.CoworkingSpace;
import com.example.springapi.service.CoworkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CoworkingSpaceService spaceService;

    @GetMapping("/panel")
    public ResponseEntity<String> adminPanel() {
        return ResponseEntity.ok("Admin Panel Active");
    }

    @PostMapping("/spaces")
    public ResponseEntity<String> addSpace(@RequestBody CoworkingSpace space) {
        spaceService.addSpace(space);
        return ResponseEntity.ok("Space added successfully");
    }

    @DeleteMapping("/spaces/{id}")
    public ResponseEntity<String> removeSpace(@PathVariable int id) {
        boolean removed = spaceService.removeSpace(id);
        return removed ?
                ResponseEntity.ok("Space removed successfully") :
                ResponseEntity.badRequest().body("Failed to remove space");
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<?>> viewAllBookings() {
        return ResponseEntity.ok(spaceService.viewAllBookings());
    }
}

