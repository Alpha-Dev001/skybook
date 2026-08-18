package dev.alpha.skybook.service.impl;

import dev.alpha.skybook.dto.request.AirportRequest;
import dev.alpha.skybook.dto.response.AirportResponse;
import dev.alpha.skybook.entity.Airport;
import dev.alpha.skybook.exception.AirportAlreadyExistsException;
import dev.alpha.skybook.mapper.AirportMapper;
import dev.alpha.skybook.repository.AirportRepository;
import dev.alpha.skybook.service.AirportService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private static final Logger log =
            LoggerFactory.getLogger(AirportServiceImpl.class);

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    @Transactional
    public AirportResponse createAirport(AirportRequest request) {

        log.info("Creating airport with code {}", request.code());

        if (airportRepository.existsByCode(request.code())) {

            log.warn("Airport with code {} already exists", request.code());

            throw new AirportAlreadyExistsException(
                    "Airport with code " + request.code() + " already exists"
            );
        }

        Airport airport = AirportMapper.toEntity(request);

        Airport savedAirport = airportRepository.save(airport);

        log.info("Airport {} created successfully", savedAirport.getCode());

        return AirportMapper.toResponse(savedAirport);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AirportResponse> getAllAirports() {

        log.info("Fetching all airports");

        return airportRepository.findAll()
                .stream()
                .map(AirportMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AirportResponse getAirportById(Long id) {

        log.info("Fetching airport with id {}", id);

        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportAlreadyExistsException(
                        "Airport with id " + id + " not found"
                ));

        return AirportMapper.toResponse(airport);
    }

    @Override
    @Transactional
    public AirportResponse updateAirport(Long id, AirportRequest request) {

        log.info("Updating airport with id {}", id);

        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportAlreadyExistsException(
                        "Airport with id " + id + " not found"
                ));

        if (!airport.getCode().equals(request.code())
                && airportRepository.existsByCode(request.code())) {

            log.warn("Airport code {} already exists", request.code());

            throw new AirportAlreadyExistsException(
                    "Airport with code " + request.code() + " already exists"
            );
        }

        airport.setCode(request.code());
        airport.setName(request.name());
        airport.setCity(request.city());
        airport.setCountry(request.country());

        Airport updatedAirport = airportRepository.save(airport);

        log.info("Airport {} updated successfully", updatedAirport.getCode());

        return AirportMapper.toResponse(updatedAirport);
    }

    @Override
    @Transactional
    public void deleteAirport(Long id) {

        log.info("Deleting airport with id {}", id);

        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportAlreadyExistsException(
                        "Airport with id " + id + " not found"
                ));

        airportRepository.delete(airport);

        log.info("Airport {} deleted successfully", airport.getCode());
    }
}