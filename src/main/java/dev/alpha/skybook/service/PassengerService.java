package dev.alpha.skybook.service;

import dev.alpha.skybook.dto.request.PassengerRequest;
import dev.alpha.skybook.dto.response.PassengerResponse;

import java.util.List;

public interface PassengerService {
    PassengerResponse createPassenger(PassengerRequest request);
    PassengerResponse getPassengerById(Long id);
    List<PassengerResponse> getAllPassengers();
    PassengerResponse updatePassenger(Long id, PassengerRequest request);
    void deletePassenger(Long id);
}