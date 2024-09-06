package com.cesar31.organization.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sa_organization")
public class OrganizationEntity {

    @Id
    @Column(name = "organization_id")
    private UUID organizationId;

    @Column(name = "parent_organization_id")
    private UUID parentOrganizationId;

    @Column(name = "organization_name")
    private String name;

    @Column(name = "organization_email")
    private String email;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "tax_identification_number")
    private String tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_organization_type")
    private CategoryEntity catOrganizationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_status")
    private CategoryEntity catStatus;
}
