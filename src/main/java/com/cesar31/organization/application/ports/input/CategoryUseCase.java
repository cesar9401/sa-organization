package com.cesar31.organization.application.ports.input;

import com.cesar31.organization.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryUseCase {

    Optional<Category> findById(Long categoryId);

    Category findBy(Long categoryId);

    List<Category> findByParentId(Long parentId);
}
