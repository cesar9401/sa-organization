-- new col
ALTER TABLE public.sa_organization ADD cat_status BIGINT;

-- update values
UPDATE public.sa_organization SET cat_status = 511;

-- set not null
ALTER TABLE public.sa_organization ALTER COLUMN cat_status SET NOT NULL;
