-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava ;
USE atvjava;

-- Tabela de produtos
CREATE TABLE produtos (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL
);

-- Tabela de categorias
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome_categoria VARCHAR(255) NOT NULL
);

-- Tabela de associação entre produtos e categorias
CREATE TABLE produtos_categorias (
    id_produto INT,
    id_categoria INT,
    PRIMARY KEY (id_produto, id_categoria),
    FOREIGN KEY (id_produto) REFERENCES produtos(id_produto),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);
