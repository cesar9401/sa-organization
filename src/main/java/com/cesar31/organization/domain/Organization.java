package com.cesar31.organization.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Organization {

    private UUID organizationId;
    private UUID parentOrganizationId;
    private String name;
    private String email;
    private String businessName;
    private String taxNumber;
    private Category catOrganizationType;
}
