package com.cesar31.organization.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.organization.domain.Organization;
import com.cesar31.organization.infrastructure.adapters.output.persistence.entity.OrganizationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {CategoryPersistenceMapper.class})
public interface OrganizationPersistenceMapper {

    @Mapping(source = "organizationId", target = "organizationId")
    @Mapping(source = "parentOrganizationId", target = "parentOrganizationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "businessName", target = "businessName")
    @Mapping(source = "tax", target = "taxNumber")
    Organization toOrganization(OrganizationEntity entity);
    List<Organization> toOrganizations(List<OrganizationEntity> entities);

    @InheritInverseConfiguration
    OrganizationEntity toOrganizationEntity(Organization organization);
}
