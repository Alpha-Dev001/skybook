package dev.alpha.skybook.repository;

import dev.alpha.skybook.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long>{
    boolean existsByRegistrationNumber(String registrationNumber);
}
