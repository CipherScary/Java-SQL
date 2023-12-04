-- Script para criar o banco de dados e as tabelas

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava;

-- Seleção do banco de dados
USE atvjava;

-- Criação da tabela 'alunos'
CREATE TABLE IF NOT EXISTS alunos (
    id_aluno INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    turma VARCHAR(50) NOT NULL
);

-- Criação da tabela 'cursos'
CREATE TABLE IF NOT EXISTS cursos (
    id_curso INT PRIMARY KEY,
    nome_curso VARCHAR(255) NOT NULL,
    instrutor VARCHAR(255) NOT NULL
);
