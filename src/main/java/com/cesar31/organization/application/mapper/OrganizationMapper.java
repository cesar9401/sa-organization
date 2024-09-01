package com.cesar31.organization.application.mapper;

import com.cesar31.organization.application.dto.CreateOrgReqDto;
import com.cesar31.organization.application.dto.UpdateOrgReqDto;
import com.cesar31.organization.domain.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrganizationMapper {

    @Mapping(source = "parentId", target = "parentOrganizationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "businessName", target = "businessName")
    @Mapping(source = "taxNumber", target = "taxNumber")
    @Mapping(target = "catOrganizationType", ignore = true)
    Organization toOrganization(CreateOrgReqDto reqDto);

    @Mapping(source = "organizationId", target = "organizationId")
    @Mapping(source = "parentId", target = "parentOrganizationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "businessName", target = "businessName")
    @Mapping(source = "taxNumber", target = "taxNumber")
    @Mapping(target = "catOrganizationType", ignore = true)
    Organization toOrganization(UpdateOrgReqDto reqDto);
}
