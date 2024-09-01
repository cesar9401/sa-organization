CREATE TABLE public.sa_category
(
    category_id        BIGINT       NOT NULL,
    parent_category_id BIGINT,
    cat_description    VARCHAR(100) NOT NULL,
    CONSTRAINT sa_category_pk PRIMARY KEY (category_id)
);
