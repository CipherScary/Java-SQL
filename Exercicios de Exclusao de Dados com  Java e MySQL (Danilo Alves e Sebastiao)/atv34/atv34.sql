CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;

CREATE TABLE eventos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    data_evento DATE,
    local VARCHAR(255)
);

CREATE TABLE participantes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    id_evento INT,
    FOREIGN KEY (id_evento) REFERENCES eventos(id) ON DELETE CASCADE
);

INSERT INTO eventos (nome, data_evento, local) VALUES
    ('Evento A', '2023-01-01', 'Local A'),
    ('Evento B', '2023-02-01', 'Local B');

INSERT INTO participantes (nome, email, id_evento) VALUES
    ('Participante 1', 'participante1@email.com', 1),
    ('Participante 2', 'participante2@email.com', 1),
    ('Participante 3', 'participante3@email.com', 2);
