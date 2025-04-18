package com.example.springapi.controller;

import com.example.springapi.entity.CoworkingSpace;
import com.example.springapi.service.CoworkingSpaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CoworkingSpaceService spaceService;

    public AdminController(CoworkingSpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/panel")
    public ResponseEntity<String> adminPanel() {
        return ResponseEntity.ok("Admin Panel Active");
    }

    @PostMapping("/spaces/add")
    public ResponseEntity<String> addSpace(@RequestBody CoworkingSpace space) {
        spaceService.addSpace(space);
        return ResponseEntity.ok("Space added successfully");
    }

    @DeleteMapping("/spaces/remove/{id}")
    public ResponseEntity<String> removeSpace(@PathVariable("id") int spaceID) {
        boolean removed = spaceService.removeSpace(spaceID);
        if (removed) {
            return ResponseEntity.ok("Space removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to remove space");
        }
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<?>> viewAllBookings() {
        return ResponseEntity.ok(spaceService.viewAllBookings());
    }
}

