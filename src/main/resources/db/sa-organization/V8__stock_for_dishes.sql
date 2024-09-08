ALTER TABLE public.sa_dish ADD COLUMN dish_stock INTEGER;

UPDATE public.sa_dish SET dish_stock = 0;

ALTER TABLE public.sa_dish ALTER COLUMN dish_stock SET NOT NULL;
