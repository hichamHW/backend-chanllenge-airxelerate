package com.airxelerate.service.impl;

import com.airxelerate.dto.request.FlightCreateRequest;
import com.airxelerate.dto.request.FlightUpdateRequest;
import com.airxelerate.dto.response.FlightResponse;
import com.airxelerate.entity.Flight;
import com.airxelerate.exception.EntityNotFoundException;
import com.airxelerate.repository.FlightRepository;
import com.airxelerate.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;


    @Override
    public FlightResponse create(FlightCreateRequest flightCreateRequest) {
        Flight createdFlight = this.flightRepository.save(flightCreateRequest.toEntity());
        return FlightResponse.fromEntity(createdFlight);
    }

    @Override
    public FlightResponse update(FlightUpdateRequest flightUpdateRequest) {
        this.flightRepository.findById(flightUpdateRequest.getId())
                .orElseThrow(()-> new EntityNotFoundException("Flight: "+flightUpdateRequest.getId()+" not found !"));
        Flight updatedFlight = this.flightRepository.save(flightUpdateRequest.toEntity());
        return FlightResponse.fromEntity(updatedFlight);
    }

    @Override
    public void delete(UUID id) {
        this.flightRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Flight: "+id+" not found !"));
        this.flightRepository.deleteById(id);
    }

    @Override
    public Page<FlightResponse> getAllFlights(Pageable pageable) {
        Page<Flight> flightPage = this.flightRepository.findAll(pageable);
        List<FlightResponse> flightResponses = flightPage
                .getContent()
                .stream()
                .map(element-> FlightResponse.fromEntity(element))
                .collect(Collectors.toList());
        return new PageImpl<>(
                flightResponses,
                pageable ,
                flightPage.getTotalElements()
        );
    }

    @Override
    public FlightResponse getFlightById(UUID id) {
        Flight flight = this.flightRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Flight: "+id+" not found !"));
       return FlightResponse.fromEntity(flight);
    }


}
