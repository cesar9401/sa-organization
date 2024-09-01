package com.cesar31.organization.infrastructure.adapters.output.persistence.repository;

import com.cesar31.organization.infrastructure.adapters.output.persistence.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationEntityRepository extends JpaRepository<OrganizationEntity, UUID> {

    Optional<OrganizationEntity> findByEmail(String email);

    Optional<OrganizationEntity> findByBusinessName(String businessName);

    Optional<OrganizationEntity> findByTax(String tax);

    Optional<OrganizationEntity> findByEmailAndOrganizationIdNot(String email, UUID organizationId);

    Optional<OrganizationEntity> findByBusinessNameAndOrganizationIdNot(String businessName, UUID organizationId);

    Optional<OrganizationEntity> findByTaxAndOrganizationIdNot(String tax, UUID organizationId);
}
