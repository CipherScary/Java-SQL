-- Script para criar o banco de dados e as tabelas

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava;

-- Seleção do banco de dados
USE atvjava;

-- Criação da tabela 'projetos'
CREATE TABLE IF NOT EXISTS projetos (
    id_projeto INT PRIMARY KEY,
    nome_projeto VARCHAR(255) NOT NULL,
    descricao TEXT
);

-- Criação da tabela 'atribuicoes'
CREATE TABLE IF NOT EXISTS atribuicoes (
    id_atribuicao INT PRIMARY KEY,
    id_projeto INT,
    id_funcionario INT,
    FOREIGN KEY (id_projeto) REFERENCES projetos(id_projeto)
);
