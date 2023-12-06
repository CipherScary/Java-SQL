CREATE DATABASE IF NOT EXISTS atvjava2;
USE atvjava2;
CREATE TABLE pacientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    idade INT,
    endereco VARCHAR(255),
    email VARCHAR(100)
);

-- Criação da tabela resultados_exames
CREATE TABLE resultados_exames (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_paciente INT,
    tipo_exame VARCHAR(100) NOT NULL,
    resultado TEXT,
    data_exame DATE,
    FOREIGN KEY (id_paciente) REFERENCES pacientes(id) ON DELETE CASCADE
);

-- Exemplo de dados de teste
INSERT INTO pacientes (nome, idade, endereco, email) VALUES
    ('Paciente A', 30, 'Rua A, 123', 'pacienteA@email.com'),
    ('Paciente B', 25, 'Rua B, 456', 'pacienteB@email.com');

INSERT INTO resultados_exames (id_paciente, tipo_exame, resultado, data_exame) VALUES
    (1, 'Exame de Sangue', 'Normal', '2023-01-01'),
    (2, 'Raio-X', 'Fratura detectada', '2023-02-01');
