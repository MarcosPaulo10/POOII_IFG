-- 1. Criar o banco de dados (se não existir)
CREATE DATABASE ifg;

-- 2. Conectar ao banco ifg (no psql use \c ifg)
\c ifg;

-- 3. Criar tabela curso (com idcurso e nomecurso)
CREATE TABLE curso (
    idcurso SERIAL PRIMARY KEY,
    nomecurso VARCHAR(100) NOT NULL
);

-- 4. Criar tabela disciplina (com iddisciplina, nomedisciplina, cargahoraria)
CREATE TABLE disciplina (
    iddisciplina SERIAL PRIMARY KEY,
    nomedisciplina VARCHAR(100) NOT NULL,
    cargahoraria INT NOT NULL
);

-- 5. Criar tabela aluno (com idaluno, nome, sexo, dt_nasc)
CREATE TABLE aluno (
    idaluno SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sexo CHAR(1) CHECK (sexo IN ('M', 'F')),
    dt_nasc DATE NOT NULL
);
