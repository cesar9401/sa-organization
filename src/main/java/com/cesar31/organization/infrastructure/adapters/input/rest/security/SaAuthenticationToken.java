package com.cesar31.organization.infrastructure.adapters.input.rest.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class SaAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final UUID organizationId;

    public SaAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UUID organizationId) {
        super(principal, credentials, authorities);
        this.organizationId = organizationId;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }
}
