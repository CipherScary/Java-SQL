CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY,
    nome VARCHAR(255),
    ultima_acao TIMESTAMP
);
CREATE TABLE IF NOT EXISTS acesso (
    id_acesso INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    acao VARCHAR(50),
    data_acesso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
DELIMITER //
CREATE TRIGGER after_insert_acesso
AFTER INSERT ON acesso
FOR EACH ROW
BEGIN
    IF NEW.acao IN ('login', 'logout') THEN
        UPDATE usuario
        SET ultima_acao = NEW.data_acesso
        WHERE id_usuario = NEW.id_usuario;
    END IF;
END;
//
DELIMITER ;
