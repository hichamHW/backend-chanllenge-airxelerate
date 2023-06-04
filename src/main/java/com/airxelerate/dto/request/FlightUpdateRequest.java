package com.airxelerate.dto.request;

import com.airxelerate.entity.Flight;
import com.airxelerate.entity.TypeFlight;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightUpdateRequest {
    @NotNull(message = "id not null")
    @JsonProperty("id")
    private UUID id;
    @NotNull(message = "price not null")
    @JsonProperty("price")
    private BigDecimal price;
    @NotNull(message = "origin not null")
    @NotBlank(message = "origin not blank")
    @JsonProperty( "origin")
    private String origin;
    @NotNull(message = "destination not null")
    @NotBlank(message = "destination not blank")
    @JsonProperty( "destination")
    private String destination;
    @NotNull(message = "departure not null")
    @JsonProperty("departure")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departure;
    @NotNull(message = "arrival not null")
    @JsonProperty("arrival")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime arrival;
    @NotNull(message = "version not null")
    @JsonProperty("version")
    private Long version;

    public Flight toEntity(){
        Flight flight = new Flight(
                null,
                this.price,
                null,
                this.origin,
                this.destination,
                this.departure,
                this.arrival
        );
        flight.setId(this.id);
        flight.setVersion(this.version);
        return flight;
    }
}
