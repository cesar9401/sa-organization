package com.cesar31.organization.infrastructure.adapters.output.persistence;

import com.cesar31.organization.application.ports.output.CategoryOutputPort;
import com.cesar31.organization.domain.Category;
import com.cesar31.organization.infrastructure.adapters.output.persistence.mapper.CategoryPersistenceMapper;
import com.cesar31.organization.infrastructure.adapters.output.persistence.repository.CategoryEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryAdapterPersistence implements CategoryOutputPort {

    private final CategoryEntityRepository entityRepository;
    private final CategoryPersistenceMapper mapper;

    public CategoryAdapterPersistence(CategoryEntityRepository entityRepository, CategoryPersistenceMapper mapper) {
        this.entityRepository = entityRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return entityRepository.findById(categoryId)
                .map(mapper::toCategory);
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return mapper.toCategoryList(entityRepository.findByParentId(parentId));
    }
}
