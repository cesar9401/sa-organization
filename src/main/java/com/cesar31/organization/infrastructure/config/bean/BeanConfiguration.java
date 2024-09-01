package com.cesar31.organization.infrastructure.config.bean;

import com.cesar31.organization.application.ports.output.CategoryOutputPort;
import com.cesar31.organization.application.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BeanConfiguration.class)
public class BeanConfiguration {

    @Bean
    CategoryService categoryService(final CategoryOutputPort outputPort) {
        return new CategoryService(outputPort);
    }
}
