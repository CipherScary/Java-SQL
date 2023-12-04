-- Script para criar o banco de dados e as tabelas

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava;

-- Seleção do banco de dados
USE atvjava;

-- Criação da tabela 'autores'
CREATE TABLE IF NOT EXISTS autores (
    id_autor INT PRIMARY KEY,
    nome_autor VARCHAR(255) NOT NULL
);

-- Criação da tabela 'livros'
CREATE TABLE IF NOT EXISTS livros (
    id_livro INT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    ano_publicacao INT NOT NULL
);
