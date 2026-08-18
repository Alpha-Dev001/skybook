package dev.alpha.skybook.repository;

import dev.alpha.skybook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}