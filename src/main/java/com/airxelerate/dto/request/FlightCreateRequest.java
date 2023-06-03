package com.airxelerate.dto.request;

import com.airxelerate.entity.Flight;
import com.airxelerate.entity.TypeFlight;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class FlightCreateRequest {
    @NotBlank
    private Long code;
    @Min(0)
    private BigDecimal price;
    @NotBlank
    private String from;
    @NotBlank
    private String to;
    @NotBlank
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private TypeFlight type;

    public Flight toEntity(){
        return new Flight(
                11L,
                this.price,
                this.from,
                this.to,
                this.departure,
                this.arrival,
                this.type
        );
    }
}
