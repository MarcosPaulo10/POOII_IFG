-- Criação da tabela EDITORA
CREATE TABLE editora (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Criação da tabela AUTOR
CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Criação da tabela LIVRO
CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    editora_id INTEGER,
    CONSTRAINT fk_livro_editora FOREIGN KEY (editora_id) REFERENCES editora(id)
);

-- Criação da tabela de junção LIVRO_AUTOR (relação N:M)
CREATE TABLE livro_autor (
    livro_id INTEGER NOT NULL,
    autor_id INTEGER NOT NULL,
    PRIMARY KEY (livro_id, autor_id),
    CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES livro(id),
    CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES autor(id)
);
