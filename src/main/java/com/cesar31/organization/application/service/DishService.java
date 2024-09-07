package com.cesar31.organization.application.service;

import com.cesar31.organization.application.dto.CreateDishReqDto;
import com.cesar31.organization.application.dto.UpdateDishReqDto;
import com.cesar31.organization.application.exception.ApplicationException;
import com.cesar31.organization.application.exception.EntityNotFoundException;
import com.cesar31.organization.application.mapper.DishMapper;
import com.cesar31.organization.application.exception.ForbiddenException;
import com.cesar31.organization.application.ports.input.DishUseCase;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.output.CurrentUserOutputPort;
import com.cesar31.organization.application.ports.output.DishOutputPort;
import com.cesar31.organization.application.util.enums.CategoryEnum;
import com.cesar31.organization.application.util.enums.RoleEnum;
import com.cesar31.organization.domain.Dish;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class DishService implements DishUseCase {

    private final DishOutputPort dishOutputPort;
    private final DishMapper mapper;
    private final OrganizationUseCase organizationUseCase;
    private final CurrentUserOutputPort currentUserOutputPort;

    private final Set<UUID> allowedRoles = Set.of(RoleEnum.ROOT.roleId, RoleEnum.RESTAURANT_MANAGER.roleId);

    public DishService(DishOutputPort dishOutputPort, DishMapper mapper, OrganizationUseCase organizationUseCase, CurrentUserOutputPort currentUserOutputPort) {
        this.dishOutputPort = dishOutputPort;
        this.mapper = mapper;
        this.organizationUseCase = organizationUseCase;
        this.currentUserOutputPort = currentUserOutputPort;
    }

    @Override
    public List<Dish> findAll() {
        return dishOutputPort.findAll(currentUserOutputPort.getOrganizationId());
    }

    @Override
    public Optional<Dish> findById(UUID dishId) {
        return dishOutputPort.findByDishIdAndOrganizationId(dishId, currentUserOutputPort.getOrganizationId());
    }

    @Override
    public Dish save(CreateDishReqDto reqDto) throws ForbiddenException, EntityNotFoundException, ApplicationException {
        reqDto.validateSelf();

        var isRestManagerOrRoot = currentUserOutputPort.hasAnyRole(allowedRoles);
        if (!isRestManagerOrRoot) throw new ForbiddenException("not_allowed_to_create_dish");

        var organizationId = currentUserOutputPort.getOrganizationId();
        var organization = organizationUseCase.findById(organizationId);
        if (organization.isEmpty()) throw new EntityNotFoundException("organization_not_found");

        var org = organization.get();
        if (!org.getCatOrganizationType().is(CategoryEnum.OT_RESTAURANT)) throw new ApplicationException("organization_is_not_a_restaurant");

        var dish = mapper.toDish(reqDto);
        dish.setDishId(UUID.randomUUID());
        dish.setOrganizationId(organizationId);
        return dishOutputPort.save(dish);
    }

    @Override
    public Dish update(UUID dishId, UpdateDishReqDto reqDto) throws ForbiddenException, EntityNotFoundException, ApplicationException {
        reqDto.validateSelf();

        var isRestManagerOrRoot = currentUserOutputPort.hasAnyRole(allowedRoles);
        if (!isRestManagerOrRoot) throw new ForbiddenException("not_allowed_to_create_dish");

        var dishOpt = this.findById(dishId);
        if (dishOpt.isEmpty()) throw new EntityNotFoundException("dish_not_found");

        var originalDish = dishOpt.get();
        if (!originalDish.getDishId().equals(reqDto.getDishId())) throw new ApplicationException("invalid_update");

        var dish = mapper.toDish(reqDto);
        dish.setOrganizationId(originalDish.getOrganizationId());
        return dishOutputPort.save(dish);
    }
}
