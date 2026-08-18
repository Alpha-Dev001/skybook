package dev.alpha.skybook.repository;

import dev.alpha.skybook.entity.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FlightRepository 
    extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    boolean existsByFlightNumber(String flightNumber);
}