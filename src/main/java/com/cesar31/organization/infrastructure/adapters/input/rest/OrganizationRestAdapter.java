package com.cesar31.organization.infrastructure.adapters.input.rest;

import com.cesar31.organization.application.dto.CreateOrgReqDto;
import com.cesar31.organization.application.dto.UpdateOrgReqDto;
import com.cesar31.organization.application.exception.ApplicationException;
import com.cesar31.organization.application.exception.EntityNotFoundException;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.domain.Organization;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizations")
public class OrganizationRestAdapter {

    private final OrganizationUseCase organizationUseCase;

    public OrganizationRestAdapter(OrganizationUseCase organizationUseCase) {
        this.organizationUseCase = organizationUseCase;
    }

    @GetMapping
    @Operation(description = "Find all organizations.")
    public ResponseEntity<List<Organization>> findAll() {
        var organizations = organizationUseCase.findAll();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("{organizationId}")
    @Operation(description = "Find any organization by its id.")
    public ResponseEntity<Organization> findById(@PathVariable("organizationId") UUID organizationId) {
        return organizationUseCase.findById(organizationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("exists/{organizationId}")
    @Operation(description = "Check if any organization exists by its id.")
    public ResponseEntity<Boolean> exists(@PathVariable("organizationId") UUID organizationId) {
        var exists = organizationUseCase.existsById(organizationId);
        return ResponseEntity.ok(exists);
    }

    @PostMapping
    @Operation(description = "Create a new organization.")
    public ResponseEntity<Organization> create(@RequestBody CreateOrgReqDto reqDto) throws ApplicationException, EntityNotFoundException {
        Organization newOrganization = null;
        try {
            newOrganization = organizationUseCase.save(reqDto);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);
    }

    @PutMapping("{organizationId}")
    @Operation(description = "Update any organization by its id.")
    public ResponseEntity<Organization> update(@PathVariable("organizationId") UUID organizationId, @RequestBody UpdateOrgReqDto reqDto) throws ApplicationException, EntityNotFoundException {
        Organization updatedOrganization = null;
        try {
            updatedOrganization = organizationUseCase.update(organizationId, reqDto);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return new ResponseEntity<>(updatedOrganization, HttpStatus.OK);
    }
}
