package com.example.springapi.service;


import com.example.springapi.entity.CoworkingSpace;
import com.example.springapi.entity.Reservation;
import com.example.springapi.repository.ReservationRepository;
import com.example.springapi.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoworkingSpaceService {

    private final SpaceRepository spaceRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public CoworkingSpaceService(SpaceRepository spaceRepository,
                                 ReservationRepository reservationRepository) {
        this.spaceRepository = spaceRepository;
        this.reservationRepository = reservationRepository;
    }

    @CacheEvict(value = "spaces", allEntries = true)
    public void addSpace(CoworkingSpace space) {
        spaceRepository.save(space);
    }

    @CacheEvict(value = "spaces", allEntries = true)
    public boolean removeSpace(int id) {
        spaceRepository.deleteById(id);
        return false;
    }

    @Cacheable("bookings")
    public List<Reservation> viewAllBookings() {
        return reservationRepository.findAll();
    }

    @Cacheable("spaces")
    public CoworkingSpace findSpaceById(int spaceId) {
        return spaceRepository.findById(spaceId).orElse(null);
    }
}
