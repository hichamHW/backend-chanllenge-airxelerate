package com.airxelerate.controller.v1;

import com.airxelerate.dto.request.FlightCreateRequest;
import com.airxelerate.dto.request.FlightUpdateRequest;
import com.airxelerate.dto.response.FlightResponse;
import com.airxelerate.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    private final FlightService flightService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<FlightResponse> create( @Valid  @RequestBody FlightCreateRequest flightCreateRequest){
        log.info("create flight: {}", flightCreateRequest);
        FlightResponse created = this.flightService.create(flightCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<FlightResponse> update ( @Valid @RequestBody FlightUpdateRequest flightUpdateRequest){
        log.info("update flight: {}", flightUpdateRequest);
        FlightResponse updated = this.flightService.update(flightUpdateRequest);
        return ResponseEntity.ok(updated);
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public ResponseEntity<Page<FlightResponse>> getAllFlights(Pageable pageable){
        log.info("get all by pageable : {}", pageable);
        Page<FlightResponse> flightResponsePage  = this.flightService.getAllFlights(pageable);
        return ResponseEntity.ok(flightResponsePage);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public ResponseEntity<FlightResponse> getFlightById(  @PathVariable("id") UUID id){
        log.info("get flight by id : {}", id);
       FlightResponse flightResponse  = this.flightService.getFlightById(id);
        return ResponseEntity.ok(flightResponse);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> delete(  @PathVariable("id") UUID id){
        log.info("delete flight by id : {}", id);
        this.flightService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
