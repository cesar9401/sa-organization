package com.cesar31.organization.application.service;

import com.cesar31.organization.application.dto.CreateOrgReqDto;
import com.cesar31.organization.application.dto.UpdateOrgReqDto;
import com.cesar31.organization.application.exception.ApplicationException;
import com.cesar31.organization.application.exception.EntityNotFoundException;
import com.cesar31.organization.application.mapper.OrganizationMapper;
import com.cesar31.organization.application.ports.input.CategoryUseCase;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.output.OrganizationOutputPort;
import com.cesar31.organization.domain.Organization;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrganizationService implements OrganizationUseCase {

    private final OrganizationOutputPort outputPort;
    private final CategoryUseCase categoryUseCase;
    private final OrganizationMapper mapper;

    public OrganizationService(OrganizationOutputPort outputPort, CategoryUseCase categoryUseCase, OrganizationMapper mapper) {
        this.outputPort = outputPort;
        this.categoryUseCase = categoryUseCase;
        this.mapper = mapper;
    }

    @Override
    public List<Organization> findAll() {
        return outputPort.findAll();
    }

    @Override
    public Optional<Organization> findById(UUID organizationId) {
        return outputPort.findById(organizationId);
    }

    @Override
    public Organization save(CreateOrgReqDto reqDto) throws ApplicationException, EntityNotFoundException {
        // validation
        reqDto.validateSelf();

        if (reqDto.getParentId() != null) {
            var parent = outputPort.findById(reqDto.getParentId());
            if (parent.isEmpty()) throw new EntityNotFoundException("parent_not_found");
        }

        var existsByEmail = outputPort.existsByEmail(reqDto.getEmail(), null);
        if (existsByEmail) throw new ApplicationException("email_already_exists");

        var category = categoryUseCase.findById(reqDto.getCatOrganizationType());
        if (category.isEmpty()) throw new EntityNotFoundException("category_not_found");

        var organization = mapper.toOrganization(reqDto);
        organization.setOrganizationId(UUID.randomUUID());
        organization.setCatOrganizationType(category.get());
        return outputPort.save(organization);
    }

    @Override
    public Organization update(UUID organizationId, UpdateOrgReqDto reqDto) throws ApplicationException, EntityNotFoundException {
        reqDto.validateSelf();

        if (reqDto.getParentId() != null) {
            var parent = outputPort.findById(reqDto.getParentId());
            if (parent.isEmpty()) throw new EntityNotFoundException("parent_not_found");
        }

        var opt = outputPort.findById(organizationId);
        if (opt.isEmpty()) throw new EntityNotFoundException("organization_not_found");

        var originalOrganization = opt.get();
        if (!originalOrganization.getOrganizationId().equals(reqDto.getOrganizationId())) throw new ApplicationException("invalid_update");

        var existsByEmail = outputPort.existsByEmail(reqDto.getEmail(), organizationId);
        if (existsByEmail) throw new ApplicationException("email_already_exists");

        var category = categoryUseCase.findById(reqDto.getCatOrganizationType());
        if (category.isEmpty()) throw new EntityNotFoundException("category_not_found");

        var organization = mapper.toOrganization(reqDto);
        organization.setCatOrganizationType(category.get());
        return outputPort.save(organization);
    }
}
