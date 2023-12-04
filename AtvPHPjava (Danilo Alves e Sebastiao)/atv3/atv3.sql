CREATE DATABASE IF NOT EXISTS atvjava;
USE atvjava;
-- Criação do banco de dados
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabela de vendas
CREATE TABLE vendas (
    id_venda INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    produto_vendido VARCHAR(255) NOT NULL,
    valor DOUBLE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);
