package com.cesar31.organization.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sa_room")
public class RoomEntity {

    @Id
    @Column(name = "room_id")
    private UUID roomId;

    @Column(name = "organization_id")
    private UUID organizationId;

    @Column(name = "room_code")
    private String code;

    @Column(name = "number_of_beds")
    private Integer numberOfBeds;

    @Column(name = "daily_price")
    private BigDecimal dailyPrice;

    @Column(name = "daily_maintenance_cost")
    private BigDecimal dailyMaintenanceCost;
}
