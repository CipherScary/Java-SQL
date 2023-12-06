CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;

CREATE TABLE autores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE livros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    ano_publicacao INT
);

CREATE TABLE autores_livros (
    id_autor INT,
    id_livro INT,
    PRIMARY KEY (id_autor, id_livro),
    FOREIGN KEY (id_autor) REFERENCES autores(id) ON DELETE CASCADE,
    FOREIGN KEY (id_livro) REFERENCES livros(id) ON DELETE CASCADE
);

INSERT INTO autores (nome) VALUES
    ('Autor A'),
    ('Autor B');

INSERT INTO livros (titulo, ano_publicacao) VALUES
    ('Livro 1', 2020),
    ('Livro 2', 2018);

INSERT INTO autores_livros (id_autor, id_livro) VALUES
    (1, 1),
    (2, 1),
    (2, 2);
