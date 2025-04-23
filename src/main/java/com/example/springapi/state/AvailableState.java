package com.example.springapi.state;

import com.example.springapi.entity.CoworkingSpace;

public class AvailableState implements SpaceState {

    @Override
    public String getStatusMessage() {
        return "Space is available for booking";
    }

    @Override
    public boolean canBeBooked() {
        return true;
    }

    @Override
    public void handleBooking(CoworkingSpace space) {
        space.setCurrentState(new ReservedState());
        space.setIsAvailable(false);
    }

    @Override
    public void handleCancellation(CoworkingSpace space) {

    }

    @Override
    public String toString() {
        return "AvailableState";
    }
}