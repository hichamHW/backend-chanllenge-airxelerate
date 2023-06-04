package com.airxelerate.dto.request;

import com.airxelerate.entity.Flight;
import com.airxelerate.entity.TypeFlight;
import com.airxelerate.util.GeneratorUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightCreateRequest {
    @NotNull(message = "price not null")
    @Min(0)
    @JsonProperty( "price")
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
    @JsonProperty( "departure")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departure;
    @NotNull(message = "arrival not null")
    @JsonProperty( "arrival")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime arrival;

    public Flight toEntity(){
        return new Flight(
                GeneratorUtil.generateRandom4DigitNumber(),
                this.price,
                GeneratorUtil.getRandomCarrierCode(),
                this.origin,
                this.destination,
                this.departure,
                this.arrival
        );
    }
}
