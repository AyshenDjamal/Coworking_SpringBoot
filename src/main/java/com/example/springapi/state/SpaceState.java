package com.example.springapi.state;

import com.example.springapi.entity.CoworkingSpace;

public interface SpaceState {
    String getStatusMessage();
    boolean canBeBooked();
    void handleBooking(CoworkingSpace space);
    void handleCancellation(CoworkingSpace space);
}
