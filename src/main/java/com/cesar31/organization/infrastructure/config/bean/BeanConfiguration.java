package com.cesar31.organization.infrastructure.config.bean;

import com.cesar31.organization.application.mapper.DishMapper;
import com.cesar31.organization.application.mapper.OrganizationMapper;
import com.cesar31.organization.application.mapper.RoomMapper;
import com.cesar31.organization.application.ports.input.CategoryUseCase;
import com.cesar31.organization.application.ports.input.DishUseCase;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.input.RoomUseCase;
import com.cesar31.organization.application.ports.output.CategoryOutputPort;
import com.cesar31.organization.application.ports.output.CurrentUserOutputPort;
import com.cesar31.organization.application.ports.output.DishOutputPort;
import com.cesar31.organization.application.ports.output.OrganizationOutputPort;
import com.cesar31.organization.application.ports.output.RoomOutputPort;
import com.cesar31.organization.application.service.CategoryService;
import com.cesar31.organization.application.service.DishService;
import com.cesar31.organization.application.service.OrganizationService;
import com.cesar31.organization.application.service.RoomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BeanConfiguration.class)
public class BeanConfiguration {

    @Bean
    CategoryUseCase categoryService(final CategoryOutputPort outputPort) {
        return new CategoryService(outputPort);
    }

    @Bean
    OrganizationUseCase organizationService(
            final OrganizationOutputPort outputPort,
            final CategoryOutputPort categoryOutputPort,
            final OrganizationMapper mapper
    ) {
        return new OrganizationService(outputPort, categoryOutputPort, mapper);
    }

    @Bean
    DishUseCase dishService(
            final DishOutputPort dishOutputPort,
            final DishMapper mapper,
            final OrganizationUseCase organizationUseCase,
            final CurrentUserOutputPort currentUserOutputPort
    ) {
        return new DishService(dishOutputPort, mapper, organizationUseCase, currentUserOutputPort);
    }

    @Bean
    RoomUseCase roomService(
            final RoomOutputPort roomOutputPort,
            final RoomMapper roomMapper,
            final OrganizationUseCase organizationUseCase,
            final CurrentUserOutputPort currentUserOutputPort
    ) {
        return new RoomService(roomOutputPort, roomMapper, organizationUseCase, currentUserOutputPort);
    }
}
