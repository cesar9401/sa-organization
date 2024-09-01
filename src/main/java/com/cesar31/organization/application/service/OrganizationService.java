package com.cesar31.organization.application.service;

import com.cesar31.organization.application.dto.CreateOrgReqDto;
import com.cesar31.organization.application.dto.UpdateOrgReqDto;
import com.cesar31.organization.application.mapper.OrganizationMapper;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.output.OrganizationOutputPort;
import com.cesar31.organization.domain.Organization;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrganizationService implements OrganizationUseCase {

    private final OrganizationOutputPort outputPort;
    private final OrganizationMapper mapper;

    public OrganizationService(OrganizationOutputPort outputPort, OrganizationMapper mapper) {
        this.outputPort = outputPort;
        this.mapper = mapper;
    }

    @Override
    public List<Organization> findAll() {
        return List.of();
    }

    @Override
    public Optional<Organization> findById(UUID organizationId) {
        return Optional.empty();
    }

    @Override
    public Organization save(CreateOrgReqDto reqDto) {
        return null;
    }

    @Override
    public Organization update(UUID organizationId, UpdateOrgReqDto reqDto) {
        return null;
    }
}
