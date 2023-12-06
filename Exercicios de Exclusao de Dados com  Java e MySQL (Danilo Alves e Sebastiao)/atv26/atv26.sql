-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava;
USE atvjava;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedidos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    produto VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE
);

INSERT INTO usuarios (nome, email) VALUES
('Usuario Teste', 'usuario@teste.com');

INSERT INTO pedidos (id_usuario, produto, quantidade) VALUES
(1, 'Produto A', 2),
(1, 'Produto B', 1);
