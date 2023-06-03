package com.airxelerate.dto.request;

import com.airxelerate.entity.Flight;
import com.airxelerate.entity.TypeFlight;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightUpdateRequest {
    @NotBlank
    @Schema(name = "id")
    private UUID id;
    @NotBlank
    @Schema(name = "code")
    private Long code;
    @NotBlank
    @Schema(name = "price")
    private BigDecimal price;
    @NotBlank
    @Schema(name = "from")
    private String from;
    @NotBlank
    @Schema(name = "to")
    private String to;
    @NotBlank
    @Schema(name = "departure")
    private LocalDateTime departure;
    @NotBlank
    @Schema(name = "arrival")
    private LocalDateTime arrival;
    @NotBlank
    @Schema(name = "type")
    private TypeFlight type;
    @NotBlank
    @Schema(name = "version")
    private Long version;

    public Flight toEntity(){
        Flight flight = new Flight(
                11L,
                this.price,
                this.from,
                this.to,
                this.departure,
                this.arrival,
                this.type
        );
        flight.setId(this.id);
        flight.setVersion(this.version);
        return flight;
    }
}
