package com.cesar31.organization.application.service;

import com.cesar31.organization.application.dto.CreateOrgReqDto;
import com.cesar31.organization.application.dto.UpdateOrgReqDto;
import com.cesar31.organization.application.exception.ForbiddenException;
import com.cesar31.organization.application.ports.output.CurrentUserOutputPort;
import com.cesar31.organization.application.util.enums.CategoryEnum;
import com.cesar31.organization.application.exception.ApplicationException;
import com.cesar31.organization.application.exception.EntityNotFoundException;
import com.cesar31.organization.application.mapper.OrganizationMapper;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.output.CategoryOutputPort;
import com.cesar31.organization.application.ports.output.OrganizationOutputPort;
import com.cesar31.organization.application.util.enums.RoleEnum;
import com.cesar31.organization.domain.Organization;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrganizationService implements OrganizationUseCase {

    private final OrganizationOutputPort outputPort;
    private final CategoryOutputPort categoryOutputPort;
    private final OrganizationMapper mapper;
    private final CurrentUserOutputPort currentUserOutputPort;

    public OrganizationService(
            OrganizationOutputPort outputPort,
            CategoryOutputPort categoryOutputPort,
            OrganizationMapper mapper,
            CurrentUserOutputPort currentUserOutputPort
    ) {
        this.outputPort = outputPort;
        this.categoryOutputPort = categoryOutputPort;
        this.mapper = mapper;
        this.currentUserOutputPort = currentUserOutputPort;
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
    public Organization save(CreateOrgReqDto reqDto) throws Exception {
        // validation
        reqDto.validateSelf();

        var isRoot = currentUserOutputPort.hasRole(RoleEnum.ROOT.roleId);
        if (!isRoot) throw new ForbiddenException("not_allowed_to_create_organizations");

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
    public Organization update(UUID organizationId, UpdateOrgReqDto reqDto) throws Exception {
        reqDto.validateSelf();

        var isRoot = currentUserOutputPort.hasRole(RoleEnum.ROOT.roleId);
        if (!isRoot) throw new ForbiddenException("not_allowed_to_update_organizations");

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
