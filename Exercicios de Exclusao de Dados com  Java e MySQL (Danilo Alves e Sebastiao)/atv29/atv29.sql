CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;

CREATE TABLE IF NOT EXISTS departamentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS funcionarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS funcionarios_departamentos (
    id_funcionario INT,
    id_departamento INT,
    PRIMARY KEY (id_funcionario, id_departamento),
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id) ON DELETE CASCADE
);

INSERT INTO departamentos (nome) VALUES
('TI'),
('RH'),
('Vendas');

INSERT INTO funcionarios (nome, cargo) VALUES
('Funcionario A', 'Desenvolvedor'),
('Funcionario B', 'Recursos Humanos');

INSERT INTO funcionarios_departamentos (id_funcionario, id_departamento) VALUES
(1, 1), -- Funcionario A pertence ao departamento de TI
(2, 2), -- Funcionario B pertence ao departamento de RH
(1, 3); -- Funcionario A pertence também ao departamento de Vendas
