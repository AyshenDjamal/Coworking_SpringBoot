package com.example.springapi.controller;

import com.example.springapi.entity.CoworkingSpace;
import com.example.springapi.entity.Reservation;
import com.example.springapi.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final ReservationService reservationService;

    public CustomerController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/panel")
    public ResponseEntity<String> customerPanel() {
        return ResponseEntity.ok("Customer Panel Active");
    }

    @GetMapping("/view-spaces")
    public ResponseEntity<List<CoworkingSpace>> viewAvailableSpaces() {
        List<CoworkingSpace> spaces = reservationService.viewSpaces();
        return ResponseEntity.ok(spaces);
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookSpace(@RequestBody Reservation reservation) {
        reservationService.bookSpace(reservation);
        return ResponseEntity.ok("Space booked successfully");
    }

    @GetMapping("/my-bookings/{customerId}")
    public ResponseEntity<List<Reservation>> myBookings(@PathVariable int customerId) {
        return ResponseEntity.ok(reservationService.myBookings(customerId));
    }

    @DeleteMapping("/cancel/{bookingID}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingID) {
        boolean canceled = reservationService.cancelBooking(bookingID);
        if (canceled) {
            return ResponseEntity.ok("Booking canceled");
        } else {
            return ResponseEntity.badRequest().body("Failed to cancel booking");
        }
    }
}

