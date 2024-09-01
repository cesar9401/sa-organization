package com.cesar31.organization.application.dto;

import com.cesar31.organization.application.util.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateOrgReqDto extends SelfValidating {

    @NotNull
    private UUID organizationId;

    @NotNull
    private UUID parentId;

    @NotNull
    @NotBlank
    private String name;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String businessName;

    @NotNull
    @NotBlank
    private String taxNumber;

    @NotNull
    @Positive
    private Long catOrganizationType;
}
