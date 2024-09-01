package com.cesar31.organization.application.ports.output;

import com.cesar31.organization.domain.Organization;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationOutputPort {

    List<Organization> findAll();

    Optional<Organization> findById(UUID organizationId);

    Organization save(Organization organization);

    Boolean existsByEmail(String email, UUID organizationId);
}
