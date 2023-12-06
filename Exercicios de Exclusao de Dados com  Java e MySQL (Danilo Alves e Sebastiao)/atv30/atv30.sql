CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;

CREATE TABLE IF NOT EXISTS projetos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS atribuicoes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_projeto INT,
    nome_funcionario VARCHAR(100) NOT NULL,
    cargo_funcionario VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_projeto) REFERENCES projetos(id) ON DELETE CASCADE
);

INSERT INTO projetos (nome, descricao) VALUES
('Projeto A', 'Descrição do Projeto A'),
('Projeto B', 'Descrição do Projeto B');

INSERT INTO atribuicoes (id_projeto, nome_funcionario, cargo_funcionario) VALUES
(1, 'Funcionario A', 'Desenvolvedor'),
(1, 'Funcionario B', 'Gerente'),
(2, 'Funcionario C', 'Analista');

