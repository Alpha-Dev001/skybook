package dev.alpha.skybook.service.impl;

import dev.alpha.skybook.dto.request.FlightRequest;
import dev.alpha.skybook.dto.response.FlightResponse;
import dev.alpha.skybook.entity.Aircraft;
import dev.alpha.skybook.entity.Airport;
import dev.alpha.skybook.entity.Flight;
import dev.alpha.skybook.exception.AircraftNotFoundException;
import dev.alpha.skybook.exception.AirportNotFoundException;
import dev.alpha.skybook.exception.FlightAlreadyExistsException;
import dev.alpha.skybook.exception.FlightNotFoundException;
import dev.alpha.skybook.exception.InvalidFlightTimeException;
import dev.alpha.skybook.exception.SameAirportException;
import dev.alpha.skybook.mapper.FlightMapper;
import dev.alpha.skybook.repository.AircraftRepository;
import dev.alpha.skybook.repository.AirportRepository;
import dev.alpha.skybook.repository.FlightRepository;
import dev.alpha.skybook.service.FlightService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;

    public FlightServiceImpl(
            FlightRepository flightRepository,
            AircraftRepository aircraftRepository,
            AirportRepository airportRepository
    ) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
        this.airportRepository = airportRepository;
    }

    // =========================
    // CREATE FLIGHT
    // =========================

    @Override
    @Transactional
    public FlightResponse createFlight(FlightRequest request) {

        // Check if flight number already exists
        if (flightRepository.existsByFlightNumber(
                request.flightNumber()
        )) {

            throw new FlightAlreadyExistsException(
                    "Flight with number "
                            + request.flightNumber()
                            + " already exists"
            );
        }

        // Departure time must be before arrival time
        if (!request.departureTime()
                .isBefore(request.arrivalTime())) {

            throw new InvalidFlightTimeException(
                    "Departure time must be before arrival time"
            );
        }

        // Departure and arrival airports must be different
        if (request.departureAirportId()
                .equals(request.arrivalAirportId())) {

            throw new SameAirportException(
                    "Departure and arrival airports must be different"
            );
        }

        // Find aircraft
        Aircraft aircraft = aircraftRepository.findById(
                request.aircraftId()
        ).orElseThrow(() ->
                new AircraftNotFoundException(
                        "Aircraft with id "
                                + request.aircraftId()
                                + " not found"
                )
        );

        // Find departure airport
        Airport departureAirport = airportRepository.findById(
                request.departureAirportId()
        ).orElseThrow(() ->
                new AirportNotFoundException(
                        "Departure airport with id "
                                + request.departureAirportId()
                                + " not found"
                )
        );

        // Find arrival airport
        Airport arrivalAirport = airportRepository.findById(
                request.arrivalAirportId()
        ).orElseThrow(() ->
                new AirportNotFoundException(
                        "Arrival airport with id "
                                + request.arrivalAirportId()
                                + " not found"
                )
        );

        // Create Flight entity
        Flight flight = new Flight();

        flight.setFlightNumber(request.flightNumber());
        flight.setAircraft(aircraft);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureTime(request.departureTime());
        flight.setArrivalTime(request.arrivalTime());
        flight.setPrice(request.price());
        flight.setStatus(request.status());

        // Save flight
        Flight savedFlight = flightRepository.save(flight);

        // Convert Entity -> Response DTO
        return FlightMapper.toResponse(savedFlight);
    }

    // =========================
    // GET ALL FLIGHTS
    // =========================

    @Override
    @Transactional(readOnly = true)
    public List<FlightResponse> getAllFlights() {

        return flightRepository.findAll()
                .stream()
                .map(FlightMapper::toResponse)
                .toList();
    }

    // =========================
    // GET FLIGHT BY ID
    // =========================

    @Override
    @Transactional(readOnly = true)
    public FlightResponse getFlightById(Long id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new FlightNotFoundException(
                                "Flight with id "
                                        + id
                                        + " not found"
                        )
                );

        return FlightMapper.toResponse(flight);
    }

    // =========================
    // UPDATE FLIGHT
    // =========================

    @Override
    @Transactional
    public FlightResponse updateFlight(
            Long id,
            FlightRequest request
    ) {

        // Find existing flight
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new FlightNotFoundException(
                                "Flight with id "
                                        + id
                                        + " not found"
                        )
                );

        // Check duplicate flight number
        if (!flight.getFlightNumber()
                .equals(request.flightNumber())
                && flightRepository.existsByFlightNumber(
                        request.flightNumber()
                )) {

            throw new FlightAlreadyExistsException(
                    "Flight with number "
                            + request.flightNumber()
                            + " already exists"
            );
        }

        // Validate times
        if (!request.departureTime()
                .isBefore(request.arrivalTime())) {

            throw new IllegalArgumentException(
                    "Departure time must be before arrival time"
            );
        }

        // Validate airports
        if (request.departureAirportId()
                .equals(request.arrivalAirportId())) {

            throw new IllegalArgumentException(
                    "Departure and arrival airports must be different"
            );
        }

        // Find aircraft
        Aircraft aircraft = aircraftRepository.findById(
                request.aircraftId()
        ).orElseThrow(() ->
                new AircraftNotFoundException(
                        "Aircraft with id "
                                + request.aircraftId()
                                + " not found"
                )
        );

        // Find departure airport
        Airport departureAirport = airportRepository.findById(
                request.departureAirportId()
        ).orElseThrow(() ->
                new AirportNotFoundException(
                        "Departure airport with id "
                                + request.departureAirportId()
                                + " not found"
                )
        );

        // Find arrival airport
        Airport arrivalAirport = airportRepository.findById(
                request.arrivalAirportId()
        ).orElseThrow(() ->
                new AirportNotFoundException(
                        "Arrival airport with id "
                                + request.arrivalAirportId()
                                + " not found"
                )
        );

        // Update existing entity
        flight.setFlightNumber(request.flightNumber());
        flight.setAircraft(aircraft);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureTime(request.departureTime());
        flight.setArrivalTime(request.arrivalTime());
        flight.setPrice(request.price());
        flight.setStatus(request.status());

        // Save updated flight
        Flight updatedFlight = flightRepository.save(flight);

        // Entity -> Response DTO
        return FlightMapper.toResponse(updatedFlight);
    }

    // =========================
    // DELETE FLIGHT
    // =========================

    @Override
    @Transactional
    public void deleteFlight(Long id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new FlightNotFoundException(
                                "Flight with id "
                                        + id
                                        + " not found"
                        )
                );

        flightRepository.delete(flight);
    }
}