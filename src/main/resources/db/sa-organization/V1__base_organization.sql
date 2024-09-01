CREATE TABLE public.sa_organization
(
    organization_id           UUID         NOT NULL,
    parent_organization_id    UUID,
    organization_name         VARCHAR(50)  NOT NULL,
    organization_email        VARCHAR(100) NOT NULL,
    business_name             VARCHAR(50)  NOT NULL,
    tax_identification_number VARCHAR(20)  NOT NULL,
    cat_organization_type     BIGINT       NOT NULL,
    CONSTRAINT sa_organization_pk PRIMARY KEY (organization_id)
);


CREATE TABLE public.sa_room_type
(
    room_type_id           UUID           NOT NULL,
    organization_id        UUID           NOT NULL,
    room_type_name         VARCHAR(100)   NOT NULL,
    number_of_beds         INTEGER        NOT NULL,
    daily_price            NUMERIC(19, 4) NOT NULL,
    daily_maintenance_cost NUMERIC(19, 4) NOT NULL,
    CONSTRAINT sa_room_type_pk PRIMARY KEY (room_type_id)
);


CREATE TABLE public.sa_room
(
    room_id         UUID        NOT NULL,
    room_type_id    UUID        NOT NULL,
    organization_id UUID        NOT NULL,
    room_code       VARCHAR(25) NOT NULL,
    CONSTRAINT sa_room_pk PRIMARY KEY (room_id)
);


CREATE TABLE public.sa_dish
(
    dish_id          UUID           NOT NULL,
    organization_id  UUID           NOT NULL,
    dish_name        VARCHAR(50)    NOT NULL,
    dish_description VARCHAR(200)   NOT NULL,
    dish_price       NUMERIC(19, 4) NOT NULL,
    CONSTRAINT sa_dish_pk PRIMARY KEY (dish_id)
);


CREATE TABLE public.sa_organization_role
(
    organization_role_id UUID           NOT NULL,
    organization_id      UUID           NOT NULL,
    name                 VARCHAR(100)   NOT NULL,
    description          VARCHAR(250)   NOT NULL,
    average_salary       NUMERIC(19, 4) NOT NULL,
    CONSTRAINT sa_organization_role_pk PRIMARY KEY (organization_role_id)
);


ALTER TABLE public.sa_organization_role
    ADD CONSTRAINT adm_organization_adm_work_position_fk
        FOREIGN KEY (organization_id)
            REFERENCES public.sa_organization (organization_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_dish
    ADD CONSTRAINT adm_organization_dish_fk
        FOREIGN KEY (organization_id)
            REFERENCES public.sa_organization (organization_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_room_type
    ADD CONSTRAINT adm_organization_room_type_fk
        FOREIGN KEY (organization_id)
            REFERENCES public.sa_organization (organization_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_room
    ADD CONSTRAINT adm_organization_room_fk
        FOREIGN KEY (organization_id)
            REFERENCES public.sa_organization (organization_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_organization
    ADD CONSTRAINT sa_organization_sa_organization_fk
        FOREIGN KEY (parent_organization_id)
            REFERENCES public.sa_organization (organization_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_room
    ADD CONSTRAINT room_type_room_fk
        FOREIGN KEY (room_type_id)
            REFERENCES public.sa_room_type (room_type_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;
