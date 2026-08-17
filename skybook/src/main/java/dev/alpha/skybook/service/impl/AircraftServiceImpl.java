package dev.alpha.skybook.service.impl;

import dev.alpha.skybook.dto.request.AircraftRequest;
import dev.alpha.skybook.dto.response.AircraftResponse;
import dev.alpha.skybook.entity.Aircraft;
import dev.alpha.skybook.exception.AircraftAlreadyExistsException;
import dev.alpha.skybook.exception.AircraftNotFoundException;
import dev.alpha.skybook.mapper.AircraftMapper;
import dev.alpha.skybook.repository.AircraftRepository;
import dev.alpha.skybook.service.AircraftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {
    private static final Logger log = LoggerFactory.getLogger(AircraftServiceImpl.class);
    private final AircraftRepository aircraftRepository;
    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    @Transactional
    public AircraftResponse createAircraft(AircraftRequest request) {
        log.info("Creating aircraft with registration number {}",request.registrationNumber());
        if (aircraftRepository.existsByRegistrationNumber(request.registrationNumber())) {
            log.warn("Aircraft {} already exists",
            request.registrationNumber());
            throw new AircraftAlreadyExistsException("Aircraft with registration number "+ request.registrationNumber()+ " already exists");
        }

        Aircraft aircraft = AircraftMapper.toEntity(request);
        Aircraft savedAircraft = aircraftRepository.save(aircraft);
        log.info("Aircraft {} created successfully",savedAircraft.getRegistrationNumber());
        return AircraftMapper.toResponse(savedAircraft);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AircraftResponse> getAllAircraft() {
        log.info("Fetching all aircraft");
        return aircraftRepository.findAll()
                .stream()
                .map(AircraftMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AircraftResponse getAircraftById(Long id) {
        log.info("Fetching aircraft {}", id);
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new AircraftNotFoundException("Aircraft with id " + id + " not found"));

        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    @Transactional
    public AircraftResponse updateAircraft(Long id,AircraftRequest request) {
        log.info("Updating aircraft {}", id);
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new AircraftNotFoundException("Aircraft with id " + id + " not found"
                ));

        if (!aircraft.getRegistrationNumber()
                .equals(request.registrationNumber())
                && aircraftRepository.existsByRegistrationNumber(
                        request.registrationNumber())) {

            throw new AircraftAlreadyExistsException(
                    "Aircraft with registration number "
                            + request.registrationNumber()
                            + " already exists"
            );
        }

        aircraft.setRegistrationNumber(request.registrationNumber());
        aircraft.setManufacturer(request.manufacturer());
        aircraft.setModel(request.model());
        aircraft.setCapacity(request.capacity());
        aircraft.setManufactureYear(request.manufactureYear());
        aircraft.setStatus(request.status());

        Aircraft updatedAircraft =
                aircraftRepository.save(aircraft);

        return AircraftMapper.toResponse(updatedAircraft);
    }

    @Override
    @Transactional
    public void deleteAircraft(Long id) {

        log.info("Deleting aircraft {}", id);

        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new AircraftNotFoundException(
                        "Aircraft with id " + id + " not found"
                ));

        aircraftRepository.delete(aircraft);

        log.info("Aircraft {} deleted",
                aircraft.getRegistrationNumber());
    }
}