package dev.alpha.skybook.repository;

import dev.alpha.skybook.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    boolean existsByEmail(String email);
    boolean existsByPassportNumber(String passportNumber);
    Optional<Passenger> findByEmail(String email);
    Optional<Passenger> findByPassportNumber(String passportNumber);
}