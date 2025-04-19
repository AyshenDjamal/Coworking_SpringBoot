package com.example.springapi.controller;

import com.example.springapi.entity.CoworkingSpace;
import com.example.springapi.entity.Reservation;
import com.example.springapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/panel")
    public ResponseEntity<String> customerPanel() {
        return ResponseEntity.ok("Customer Panel Active");
    }

    @GetMapping("/spaces")
    public ResponseEntity<List<CoworkingSpace>> viewAvailableSpaces() {
        return ResponseEntity.ok(reservationService.viewSpaces());
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> bookSpace(@RequestBody Reservation reservation) {
        reservationService.bookSpace(reservation);
        return ResponseEntity.ok("Space booked successfully");
    }

    @GetMapping("/bookings/{customerId}")
    public ResponseEntity<List<Reservation>> myBookings(@PathVariable int customerId) {
        return ResponseEntity.ok(reservationService.myBookings(customerId));
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        boolean canceled = reservationService.cancelBooking(bookingId);
        return canceled ?
                ResponseEntity.ok("Booking canceled") :
                ResponseEntity.badRequest().body("Failed to cancel booking");
    }
}