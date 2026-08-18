package dev.alpha.skybook.service.impl;

import dev.alpha.skybook.dto.request.BookingRequest;
import dev.alpha.skybook.dto.response.BookingResponse;
import dev.alpha.skybook.entity.Booking;
import dev.alpha.skybook.entity.Flight;
import dev.alpha.skybook.entity.Passenger;
import dev.alpha.skybook.exception.BookingNotFoundException;
import dev.alpha.skybook.exception.FlightNotFoundException;
import dev.alpha.skybook.exception.PassengerNotFoundException;
import dev.alpha.skybook.mapper.BookingMapper;
import dev.alpha.skybook.repository.BookingRepository;
import dev.alpha.skybook.repository.FlightRepository;
import dev.alpha.skybook.repository.PassengerRepository;
import dev.alpha.skybook.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            PassengerRepository passengerRepository,
            FlightRepository flightRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public BookingResponse createBooking(BookingRequest request) {

        Passenger passenger = passengerRepository
                .findById(request.passengerId())
                .orElseThrow(() ->
                        new PassengerNotFoundException(
                                "Passenger not found with id: "
                                        + request.passengerId()
                        )
                );

        Flight flight = flightRepository
                .findById(request.flightId())
                .orElseThrow(() ->
                        new FlightNotFoundException(
                                "Flight not found with id: "
                                        + request.flightId()
                        )
                );

        Booking booking = BookingMapper.toEntity(
                request,
                passenger,
                flight
        );

        Booking savedBooking = bookingRepository.save(booking);

        return BookingMapper.toResponse(savedBooking);
    }

    @Override
    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with id: " + id
                        )
                );

        return BookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        return bookingRepository
                .findAll()
                .stream()
                .map(BookingMapper::toResponse)
                .toList();
    }

    @Override
    public BookingResponse updateBooking(
            Long id,
            BookingRequest request
    ) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with id: " + id
                        )
                );

        Passenger passenger = passengerRepository
                .findById(request.passengerId())
                .orElseThrow(() ->
                        new PassengerNotFoundException(
                                "Passenger not found with id: "
                                        + request.passengerId()
                        )
                );

        Flight flight = flightRepository
                .findById(request.flightId())
                .orElseThrow(() ->
                        new FlightNotFoundException(
                                "Flight not found with id: "
                                        + request.flightId()
                        )
                );

        BookingMapper.updateEntity(
                booking,
                request,
                passenger,
                flight
        );

        Booking updatedBooking = bookingRepository.save(booking);

        return BookingMapper.toResponse(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with id: " + id
                        )
                );

        bookingRepository.delete(booking);
    }
}