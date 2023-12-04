-- Script para criar o banco de dados e as tabelas

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS atvjava;

-- Seleção do banco de dados
USE atvjava;

-- Criação da tabela 'resultados_exames'
CREATE TABLE IF NOT EXISTS resultados_exames (
    id_resultado INT PRIMARY KEY,
    tipo_exame VARCHAR(255) NOT NULL,
    resultado VARCHAR(255) NOT NULL
);

-- Criação da tabela 'pacientes'
CREATE TABLE IF NOT EXISTS pacientes (
    id_paciente INT PRIMARY KEY,
    nome_paciente VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL
);
