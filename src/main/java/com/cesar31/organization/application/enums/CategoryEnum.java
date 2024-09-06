package com.cesar31.organization.application.enums;

public enum CategoryEnum {

    ORGANIZATION_TYPE(500L),
        OT_HOTEL(501L),
        OT_RESTAURANT(501L),
    ENTITY_STATUS(510L),
        ES_ACTIVE(511L),
        ES_INACTIVE(512L),
        ES_DELETED(513L),
        ES_LOCKED(514L)
    ;

    public final Long categoryId;

    CategoryEnum(final Long categoryId) {
        this.categoryId = categoryId;
    }
}
