package com.airxelerate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "flights")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE flights SET deleted = true WHERE id = ?")
public class Flight  extends BaseEntity{
    private Long code;
    private BigDecimal price;
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private TypeFlight type;
}
