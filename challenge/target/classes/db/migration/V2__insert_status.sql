DELETE FROM public.status
WHERE id  = 1
  AND EXISTS(SELECT 1 FROM public.status WHERE id  = 1 LIMIT 1);

DELETE FROM public.status
WHERE id  = 2
  AND EXISTS(SELECT 1 FROM public.status WHERE id  = 2 LIMIT 1);


INSERT INTO public.status (id,"name") VALUES (1,'ATIVO');
INSERT INTO public.status (id,"name") VALUES (2,'INATIVO');