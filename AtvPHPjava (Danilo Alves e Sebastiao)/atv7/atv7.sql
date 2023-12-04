-- Script para criar o banco de dados e as tabelas

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava;

-- Seleção do banco de dados
USE atvjava;

-- Criação da tabela 'fornecedores'
CREATE TABLE IF NOT EXISTS fornecedores (
    id_fornecedor INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    contato VARCHAR(255) NOT NULL
);

-- Criação da tabela 'compras'
CREATE TABLE IF NOT EXISTS compras (
    id_compra INT PRIMARY KEY,
    id_fornecedor INT,
    produto_comprado VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id_fornecedor)
);
