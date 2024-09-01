package com.cesar31.organization.application.ports.input;

import com.cesar31.organization.application.dto.CreateOrgReqDto;
import com.cesar31.organization.application.dto.UpdateOrgReqDto;
import com.cesar31.organization.domain.Organization;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationUseCase {

    List<Organization> findAll();

    Optional<Organization> findById(UUID organizationId);

    Organization save(CreateOrgReqDto reqDto);

    Organization update(UUID organizationId, UpdateOrgReqDto reqDto);
}
