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
@Table(name = "sa_dish")
public class DishEntity {

    @Id
    @Column(name = "dish_id")
    private UUID dishId;

    @Column(name = "organization_id")
    private UUID organizationId;

    @Column(name = "dish_name")
    private String name;

    @Column(name = "dish_description")
    private String description;

    @Column(name = "dish_stock")
    private Integer stock;

    @Column(name = "dish_price")
    private BigDecimal price;
}
