-- Script para criar o banco de dados e as tabelas

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava;

-- Seleção do banco de dados
USE atvjava;

-- Criação da tabela 'eventos'
CREATE TABLE IF NOT EXISTS eventos (
    id_evento INT PRIMARY KEY,
    nome_evento VARCHAR(255) NOT NULL,
    data DATE NOT NULL
);

-- Criação da tabela 'participantes'
CREATE TABLE IF NOT EXISTS participantes (
    id_participante INT PRIMARY KEY,
    id_evento INT,
    nome_participante VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento)
);
