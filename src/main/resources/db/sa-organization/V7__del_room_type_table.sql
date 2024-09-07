-- remove constraints
ALTER TABLE public.sa_room_type DROP CONSTRAINT adm_organization_room_type_fk;
ALTER TABLE public.sa_room DROP CONSTRAINT room_type_room_fk;

-- drop table
DROP TABLE public.sa_room_type;

-- remove cols
ALTER TABLE public.sa_room DROP COLUMN room_type_id;

-- adding new cols to public.sa_room
ALTER TABLE public.sa_room ADD number_of_beds INTEGER;
ALTER TABLE public.sa_room ADD daily_price NUMERIC (19, 4);
ALTER TABLE public.sa_room ADD daily_maintenance_cost NUMERIC(19, 4);

-- update values
UPDATE public.sa_room SET number_of_beds = 1;
UPDATE public.sa_room SET daily_price = 100.00;
UPDATE public.sa_room SET daily_maintenance_cost = 50.00;

-- set to null null
ALTER TABLE public.sa_room ALTER number_of_beds SET NOT NULL;
ALTER TABLE public.sa_room ALTER daily_price SET NOT NULL;
ALTER TABLE public.sa_room ALTER daily_maintenance_cost SET NOT NULL;
