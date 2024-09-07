package com.cesar31.organization.application.service;

import com.cesar31.organization.application.dto.CreateOrgReqDto;
import com.cesar31.organization.application.dto.UpdateOrgReqDto;
import com.cesar31.organization.application.enums.CategoryEnum;
import com.cesar31.organization.application.exception.ApplicationException;
import com.cesar31.organization.application.exception.EntityNotFoundException;
import com.cesar31.organization.application.mapper.OrganizationMapper;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.output.CategoryOutputPort;
import com.cesar31.organization.application.ports.output.OrganizationOutputPort;
import com.cesar31.organization.domain.Organization;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrganizationService implements OrganizationUseCase {

    private final OrganizationOutputPort outputPort;
    private final CategoryOutputPort categoryOutputPort;
    private final OrganizationMapper mapper;

    public OrganizationService(OrganizationOutputPort outputPort, CategoryOutputPort categoryOutputPort, OrganizationMapper mapper) {
        this.outputPort = outputPort;
        this.categoryOutputPort = categoryOutputPort;
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
    public Boolean existsById(UUID organizationId) {
        return outputPort.findById(organizationId).isPresent();
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

        var catOrgType = categoryOutputPort.findById(reqDto.getCatOrganizationType());
        if (catOrgType.isEmpty()) throw new EntityNotFoundException("category_not_found");

        var catStatus = categoryOutputPort.findBy(CategoryEnum.ES_ACTIVE.categoryId);

        var organization = mapper.toOrganization(reqDto);
        organization.setOrganizationId(UUID.randomUUID());
        organization.setCatOrganizationType(catOrgType.get());
        organization.setCatStatus(catStatus);
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

        if (originalOrganization.getCatStatus().is(CategoryEnum.ES_LOCKED)) throw new EntityNotFoundException("organization_locked");

        var existsByEmail = outputPort.existsByEmail(reqDto.getEmail(), organizationId);
        if (existsByEmail) throw new ApplicationException("email_already_exists");

        var category = categoryOutputPort.findById(reqDto.getCatOrganizationType());
        if (category.isEmpty()) throw new EntityNotFoundException("category_not_found");

        var organization = mapper.toOrganization(reqDto);
        organization.setCatOrganizationType(category.get());
        organization.setCatStatus(originalOrganization.getCatStatus());
        return outputPort.save(organization);
    }
}
