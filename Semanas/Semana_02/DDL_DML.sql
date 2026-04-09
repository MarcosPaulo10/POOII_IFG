DROP TABLE IF EXISTS public.aluno;

CREATE TABLE IF NOT EXISTS public.aluno
(
    id SERIAL PRIMARY KEY,
    nome character varying(20) COLLATE pg_catalog."default",
    sexo character varying(20) COLLATE pg_catalog."default",
    dt_nasc date
);

INSERT INTO aluno VALUES (1, 'Dory', 'Masculino', '1-6-1986');
INSERT INTO aluno VALUES (2, 'Carol', 'Feminino', '2-4-1981');
INSERT INTO aluno VALUES (3, 'Isabele', 'Feminino', '8-7-2010');

SELECT * FROM aluno;