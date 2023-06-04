package com.airxelerate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "flights")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE flights SET deleted = true WHERE id = ?")
public class Flight  extends BaseEntity{
    @Column(name = "code", nullable = false, unique = true, updatable = false)
    private Long code;
    private BigDecimal price;
    @Column(name = "carrier_code", nullable = false, updatable = false)
    private String carrierCode;
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
}
