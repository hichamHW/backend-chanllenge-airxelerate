package com.airxelerate.service.impl;

import com.airxelerate.dto.request.FlightCreateRequest;
import com.airxelerate.dto.request.FlightUpdateRequest;
import com.airxelerate.dto.response.FlightResponse;
import com.airxelerate.entity.Flight;
import com.airxelerate.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class FlightServiceImplTest {

        @Mock
        private FlightRepository flightRepository;

        @InjectMocks
        private FlightServiceImpl flightService;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testCreateFlight() {
            // Prepare test data
            FlightCreateRequest createRequest =  FlightCreateRequest.
                    builder()
                            .
                    build();
            ;
            when(flightRepository.save(any(Flight.class))).thenReturn(createRequest.toEntity());

            // Perform the operation
            FlightResponse response = flightService.create(createRequest);

            // Verify the result
            verify(flightRepository, times(1)).save(any(Flight.class));
            Assertions.assertEquals(createRequest.getArrival(), response.getArrival());
            Assertions.assertEquals(createRequest.getDeparture(), response.getDeparture());
            Assertions.assertEquals(createRequest.getOrigin(), response.getOrigin());
            Assertions.assertEquals(createRequest.getDestination(), response.getDestination());
        }

        @Test
        public void testUpdateFlight() {
            // Prepare test data
            UUID flightId = UUID.randomUUID();
            FlightUpdateRequest updateRequest = new FlightUpdateRequest( /* provide necessary data */);
            Flight existingFlight = new Flight(/* provide necessary data */);
            when(flightRepository.findById(flightId)).thenReturn(Optional.of(existingFlight));
            when(flightRepository.save(any(Flight.class))).thenReturn(existingFlight);
            // Perform the operation
            FlightResponse response = flightService.update(updateRequest);
            // Verify the result
            verify(flightRepository, times(1)).findById(flightId);
            verify(flightRepository, times(1)).save(any(Flight.class));
            Assertions.assertEquals(existingFlight.getId(), response.getId());
            // Add more assertions for other properties if necessary
        }

        @Test
        public void testDeleteFlight() {
            // Prepare test data
            UUID flightId = UUID.randomUUID();
            Flight existingFlight = new Flight(/* provide necessary data */);
            when(flightRepository.findById(flightId)).thenReturn(Optional.of(existingFlight));

            // Perform the operation
            flightService.delete(flightId);

            // Verify the result
            verify(flightRepository, times(1)).findById(flightId);
            verify(flightRepository, times(1)).deleteById(flightId);
        }

        @Test
        public void testGetAllFlights() {
            // Prepare test data
            List<Flight> flightList = Arrays.asList(new Flight(/* provide necessary data */), new Flight(/* provide necessary data */));
            Pageable pageable = mock(Pageable.class);
            when(flightRepository.findAll(pageable)).thenReturn(new PageImpl<>(flightList));

            // Perform the operation
            Page<FlightResponse> responsePage = flightService.getAllFlights(pageable);

            // Verify the result
            verify(flightRepository, times(1)).findAll(pageable);
            Assertions.assertEquals(flightList.size(), responsePage.getContent().size());
            // Add more assertions for other properties if necessary
        }

        @Test
        public void testGetFlightById() {
            // Prepare test data
            UUID flightId = UUID.randomUUID();
            Flight existingFlight = new Flight(/* provide necessary data */);
            when(flightRepository.findById(flightId)).thenReturn(Optional.of(existingFlight));

            // Perform the operation
            FlightResponse response = flightService.getFlightById(flightId);

            // Verify the result
            verify(flightRepository, times(1)).findById(flightId);
            Assertions.assertEquals(existingFlight.getId(), response.getId());
            // Add more assertions for other properties if necessary
        }

}