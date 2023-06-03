package com.airxelerate.service;

import com.airxelerate.dto.request.FlightCreateRequest;
import com.airxelerate.dto.request.FlightUpdateRequest;
import com.airxelerate.dto.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FlightService {
    FlightResponse create(FlightCreateRequest flightCreateRequest);
    FlightResponse update(FlightUpdateRequest flightUpdateRequest);
    void delete(UUID id);
    Page<FlightResponse> getAllFlights(Pageable pageable);
    FlightResponse getFlightById(UUID id);
}
