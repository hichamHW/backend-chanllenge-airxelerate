package com.airxelerate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private boolean deleted;
    @Version
    private Long version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    private void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void onUpdate(){
        this.createdAt = LocalDateTime.now();
    }
}
