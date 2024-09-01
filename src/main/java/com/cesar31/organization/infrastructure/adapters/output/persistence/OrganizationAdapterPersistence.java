package com.cesar31.organization.infrastructure.adapters.output.persistence;

import com.cesar31.organization.application.ports.output.OrganizationOutputPort;
import com.cesar31.organization.domain.Organization;
import com.cesar31.organization.infrastructure.adapters.output.persistence.mapper.OrganizationPersistenceMapper;
import com.cesar31.organization.infrastructure.adapters.output.persistence.repository.OrganizationEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrganizationAdapterPersistence implements OrganizationOutputPort {

    private final OrganizationEntityRepository entityRepository;
    private final OrganizationPersistenceMapper mapper;

    public OrganizationAdapterPersistence(OrganizationEntityRepository entityRepository, OrganizationPersistenceMapper mapper) {
        this.entityRepository = entityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Organization> findAll() {
        var organizations = entityRepository.findAll();
        return mapper.toOrganizations(organizations);
    }

    @Override
    public Optional<Organization> findById(UUID organizationId) {
        return entityRepository.findById(organizationId)
                .map(mapper::toOrganization);
    }

    @Override
    public Organization save(Organization organization) {
        var entity = mapper.toOrganizationEntity(organization);
        var newEntity = entityRepository.save(entity);
        return mapper.toOrganization(newEntity);
    }

    @Override
    public Boolean existsByEmail(String email, UUID organizationId) {
        var optional = organizationId == null
                ? entityRepository.findByEmail(email)
                : entityRepository.findByEmailAndOrganizationIdNot(email, organizationId);

        return optional.isPresent();
    }
}
