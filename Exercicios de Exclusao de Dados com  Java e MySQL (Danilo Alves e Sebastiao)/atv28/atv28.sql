CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;

CREATE TABLE IF NOT EXISTS clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS vendas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT,
    produto VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE CASCADE
);

INSERT INTO clientes (nome, email) VALUES
('Cliente Teste', 'cliente@teste.com');

INSERT INTO vendas (id_cliente, produto, valor) VALUES
(1, 'Produto A', 50.00),
(1, 'Produto B', 30.00);
