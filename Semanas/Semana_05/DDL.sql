CREATE TABLE IF NOT EXISTS disciplina (
    idDisciplina SERIAL PRIMARY KEY,
    nomedisciplina VARCHAR(60) NOT NULL,
    cargahoraria INT NOT NULL
);

SELECT * FROM disciplina;
