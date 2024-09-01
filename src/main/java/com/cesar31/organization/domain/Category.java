package com.cesar31.organization.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private Long categoryId;
    private Long parentCategoryId;
    private String description;
}
