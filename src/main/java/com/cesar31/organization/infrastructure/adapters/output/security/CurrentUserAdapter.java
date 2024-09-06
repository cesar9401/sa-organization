package com.cesar31.organization.infrastructure.adapters.output.security;

import com.cesar31.organization.application.ports.output.CurrentUserOutputPort;
import com.cesar31.organization.infrastructure.adapters.input.rest.security.SaAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CurrentUserAdapter implements CurrentUserOutputPort {

    @Override
    public UUID getUserId() {
        SaAuthenticationToken authentication = (SaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getUserId();
    }

    @Override
    public UUID getOrganizationId() {
        SaAuthenticationToken authentication = (SaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getOrganizationId();
    }
}
