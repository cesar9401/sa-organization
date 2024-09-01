package com.cesar31.organization.application.enums;

public enum CategoryEnum {

    ORGANIZATION_TYPE(500L),
        OT_HOTEL(501L),
        OT_RESTAURANT(501L);

    public final Long categoryId;

    CategoryEnum(final Long categoryId) {
        this.categoryId = categoryId;
    }
}
