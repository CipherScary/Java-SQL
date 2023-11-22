CREATE TABLE IF NOT EXISTS estoque (
    id_item INT PRIMARY KEY,
    quantidade INT,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS historico_estoque (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_item INT,
    quantidade_anterior INT,
    quantidade_atual INT,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_item) REFERENCES estoque(id_item)
);
DELIMITER //
CREATE TRIGGER after_update_estoque
AFTER UPDATE ON estoque
FOR EACH ROW
BEGIN
    IF OLD.quantidade != NEW.quantidade THEN
        INSERT INTO historico_estoque (id_item, quantidade_anterior, quantidade_atual)
        VALUES (NEW.id_item, OLD.quantidade, NEW.quantidade);
    END IF;
END;
//
DELIMITER ;
