DELETE FROM public.rental_entity;
DELETE FROM public.rental_period_entity;
DELETE FROM public.car_entity;
DELETE FROM public.customer_entity;

INSERT INTO public.car_entity (id, available, model) VALUES(1, true, 'Mercedes GLC');
INSERT INTO public.customer_entity(id, email, "name") VALUES(1, 'zmi.amn@gmail.com', 'Amine ZAMANI');