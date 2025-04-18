package com.example.springapi.repository;


import com.example.springapi.entity.Reservation;
import com.example.springapi.entity.CoworkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findBySpace(CoworkingSpace space);


    List<Reservation> findByCustomerName(String customerName);


    @Query("SELECT r FROM Reservation r WHERE r.space.spaceID = :spaceId")
    List<Reservation> findBySpaceId(int spaceId);


    @Query("SELECT r FROM Reservation r WHERE r.date BETWEEN :startDate AND :endDate")
    List<Reservation> findReservationsBetweenDates(String startDate, String endDate);


    List<Reservation> findAllByOrderByDateAsc();
}
