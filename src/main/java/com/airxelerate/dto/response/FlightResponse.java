package com.airxelerate.dto.response;

import com.airxelerate.entity.Flight;
import com.airxelerate.entity.TypeFlight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightResponse {
    private UUID id ;
    private Long code;
    private BigDecimal price;
    private String from;
    private String to;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private TypeFlight type;
    private Long version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static  FlightResponse fromEntity(Flight flight){
        return new FlightResponse(
                flight.getId(),
                flight.getCode(),
                flight.getPrice(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getType(),
                flight.getVersion(),
                flight.getCreatedAt(),
                flight.getUpdatedAt()
        );
    }
}
