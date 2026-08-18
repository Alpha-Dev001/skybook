package dev.alpha.skybook.repository;

import dev.alpha.skybook.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    boolean  existsByCode(String code);
}
