package com.cesar31.organization.infrastructure.config.bean;

import com.cesar31.organization.application.mapper.OrganizationMapper;
import com.cesar31.organization.application.ports.input.CategoryUseCase;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.output.CategoryOutputPort;
import com.cesar31.organization.application.ports.output.OrganizationOutputPort;
import com.cesar31.organization.application.service.CategoryService;
import com.cesar31.organization.application.service.OrganizationService;
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
    OrganizationUseCase organizationService(final OrganizationOutputPort outputPort, final CategoryService categoryService, final OrganizationMapper mapper) {
        return new OrganizationService(outputPort, categoryService, mapper);
    }
}
