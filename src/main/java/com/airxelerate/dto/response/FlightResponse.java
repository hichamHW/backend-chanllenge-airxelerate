package com.airxelerate.dto.response;

import com.airxelerate.entity.Flight;
import com.airxelerate.entity.TypeFlight;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonProperty("id")
    private UUID id ;
    @JsonProperty("code")
    private Long code;
    @JsonProperty("carrierCode")
    private String carrierCode;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("departure")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime departure;
    @JsonProperty("arrival")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime arrival;
    @JsonProperty("version")
    private Long version;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static  FlightResponse fromEntity(Flight flight){
        return new FlightResponse(
                flight.getId(),
                flight.getCode(),
                flight.getCarrierCode(),
                flight.getPrice(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getVersion(),
                flight.getCreatedAt(),
                flight.getUpdatedAt()
        );
    }
}
