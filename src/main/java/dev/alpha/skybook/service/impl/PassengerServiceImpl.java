package dev.alpha.skybook.service.impl;

import dev.alpha.skybook.dto.request.PassengerRequest;
import dev.alpha.skybook.dto.response.PassengerResponse;
import dev.alpha.skybook.entity.Passenger;
import dev.alpha.skybook.exception.PassengerAlreadyExistsException;
import dev.alpha.skybook.exception.PassengerNotFoundException;
import dev.alpha.skybook.mapper.PassengerMapper;
import dev.alpha.skybook.repository.PassengerRepository;
import dev.alpha.skybook.service.PassengerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public PassengerResponse createPassenger(
            PassengerRequest request
    ) {

        if (passengerRepository.existsByEmail(request.email())) {
            throw new PassengerAlreadyExistsException("Passenger with email already exists: " + request.email());
        }

        if (passengerRepository.existsByPassportNumber(request.passportNumber())) {
            throw new PassengerAlreadyExistsException("Passenger with passport number already exists: "+ request.passportNumber());
        }
        Passenger passenger = PassengerMapper.toEntity(request);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return PassengerMapper.toResponse(savedPassenger);
    }

    @Override
    public PassengerResponse getPassengerById(Long id) {

        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> 
           new PassengerNotFoundException("Passenger not found with id: " + id));
        return PassengerMapper.toResponse(passenger);
    }

    @Override
    public List<PassengerResponse> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .map(PassengerMapper::toResponse)
                .toList();
    }

    @Override
    public PassengerResponse updatePassenger(
            Long id,
            PassengerRequest request
    ) {

        Passenger passenger = passengerRepository.findById(id).orElseThrow(() ->
                                new PassengerNotFoundException("Passenger not found with id: "  + id ) );

        if (!passenger.getEmail().equals(request.email())
                && passengerRepository.existsByEmail(request.email() )) {
            throw new PassengerAlreadyExistsException("Passenger with email already exists: "+ request.email() );
        }

        if (!passenger.getPassportNumber().equals(request.passportNumber())&& passengerRepository.existsByPassportNumber(request.passportNumber()
        )) {
            throw new PassengerAlreadyExistsException("Passenger with passport number already exists: "  + request.passportNumber() );
        }

        PassengerMapper.updateEntity( passenger,request);
        Passenger updatedPassenger = passengerRepository.save(passenger);
        return PassengerMapper.toResponse( updatedPassenger);
    }

    @Override
    public void deletePassenger(Long id) {

        Passenger passenger = passengerRepository.findById(id) .orElseThrow(() ->
                                new PassengerNotFoundException("Passenger not found with id: " + id ) );
        passengerRepository.delete(passenger);
    }
}