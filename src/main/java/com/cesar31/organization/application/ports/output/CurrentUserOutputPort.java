package com.cesar31.organization.application.ports.output;

import java.util.UUID;

public interface CurrentUserOutputPort {

    UUID getUserId();
    UUID getOrganizationId();
}
