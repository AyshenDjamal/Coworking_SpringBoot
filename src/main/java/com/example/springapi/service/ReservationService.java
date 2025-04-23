package com.example.springapi.service;

import com.example.springapi.entity.CoworkingSpace;
import com.example.springapi.entity.Reservation;
import com.example.springapi.repository.SpaceRepository;
import com.example.springapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SpaceRepository spaceRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              SpaceRepository spaceRepository) {
        this.reservationRepository = reservationRepository;
        this.spaceRepository = spaceRepository;
    }

    @Cacheable("availableSpaces")
    public List<CoworkingSpace> viewSpaces() {
        return spaceRepository.findByIsAvailableTrue();
    }

    @Transactional
    @CacheEvict(value = {"availableSpaces", "bookings"}, allEntries = true)
    public void bookSpace(Reservation reservation) {
        CoworkingSpace space = reservation.getSpace();
        if (!space.canBeBooked()) {
            throw new IllegalStateException(space.checkAvailability());
        }
        space.setIsAvailable(false);
        spaceRepository.save(space);
        reservationRepository.save(reservation);
    }

    @Cacheable("bookings")
    public List<Reservation> myBookings(int customerId) {
        return reservationRepository.findByCustomerName(String.valueOf(customerId));
    }

    @Transactional
    @CacheEvict(value = {"availableSpaces", "bookings"}, allEntries = true)
    public boolean cancelBooking(int bookingId) {
        Reservation reservation = reservationRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        CoworkingSpace space = reservation.getSpace();
        space.setIsAvailable(true);
        spaceRepository.save(space);

        reservationRepository.delete(reservation);
        return true;
    }
}