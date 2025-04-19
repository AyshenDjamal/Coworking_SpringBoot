package com.example.springapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workspaces")
public class CoworkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "space_id")
    private Integer spaceID;

    @NotBlank(message = "Space type is required")
    @Size(min = 2, max = 50, message = "Space type must be between 2-50 characters")
    @Column(name = "space_type", nullable = false)
    private String spaceType;

    @NotNull(message = "Price per hour is required")
    @Positive(message = "Price must be positive")
    @Column(name = "price_per_hour", nullable = false)
    private double pricePerHour;

    @Column(name = "availability_status", nullable = false)
    private boolean isAvailable = true;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    // Constructors
    public CoworkingSpace() {}

    public CoworkingSpace(String spaceType, double pricePerHour) {
        this.spaceType = spaceType;
        this.pricePerHour = pricePerHour;
    }

    // Getters and Setters
    public int getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }



    @Override
    public String toString() {
        return "CoworkingSpace{" +
                "spaceID=" + spaceID +
                ", spaceType='" + spaceType + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", isAvailable=" + isAvailable +
                '}';
    }
}