package com.example.springapi.state;

import com.example.springapi.entity.CoworkingSpace;

public class ReservedState implements SpaceState {

    @Override
    public String getStatusMessage() {
        return "Space is currently reserved";
    }

    @Override
    public boolean canBeBooked() {
        return false;
    }

    @Override
    public void handleBooking(CoworkingSpace space) {
        throw new IllegalStateException("Cannot book an already reserved space");
    }

    @Override
    public void handleCancellation(CoworkingSpace space) {
        space.setCurrentState(new AvailableState());
        space.setIsAvailable(true);
    }

    @Override
    public String toString() {
        return "ReservedState";
    }
}
