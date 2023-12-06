CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;
CREATE TABLE fornecedores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20)
);

CREATE TABLE compras (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_fornecedor INT,
    produto VARCHAR(100) NOT NULL,
    quantidade INT,
    preco DECIMAL(10, 2),
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id) ON DELETE CASCADE
);

INSERT INTO fornecedores (nome, endereco, telefone) VALUES
    ('Fornecedor A', 'Rua A, 123', '123456789'),
    ('Fornecedor B', 'Rua B, 456', '987654321');

INSERT INTO compras (id_fornecedor, produto, quantidade, preco) VALUES
    (1, 'Produto 1', 10, 50.00),
    (1, 'Produto 2', 5, 30.00),
    (2, 'Produto 3', 8, 40.00);
