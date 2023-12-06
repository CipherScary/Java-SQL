CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;

CREATE TABLE IF NOT EXISTS categorias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS categorias_produtos (
    id_categoria INT,
    id_produto INT,
    PRIMARY KEY (id_categoria, id_produto),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id) ON DELETE CASCADE,
    FOREIGN KEY (id_produto) REFERENCES produtos(id) ON DELETE CASCADE
);

INSERT INTO categorias (nome) VALUES
('Eletrônicos'),
('Roupas'),
('Alimentos');

INSERT INTO produtos (nome, preco) VALUES
('Smartphone', 999.99),
('Camiseta', 19.99),
('Chocolate', 4.99);

INSERT INTO categorias_produtos (id_categoria, id_produto) VALUES
(1, 1), -- Smartphone pertence à categoria Eletrônicos
(2, 2), -- Camiseta pertence à categoria Roupas
(3, 3); -- Chocolate pertence à categoria Alimentos
