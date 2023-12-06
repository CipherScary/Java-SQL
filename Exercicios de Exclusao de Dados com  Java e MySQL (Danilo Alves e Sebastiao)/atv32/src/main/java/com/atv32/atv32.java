// Danilo Alves e Sebastiao
package com.atv32;

import java.sql.*;

public class atv32 {

    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/atvjava2";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static void main(String[] args) {
        // ID do fornecedor que será excluído
        int idFornecedorParaExcluir = 2; // Substitua pelo ID desejado

        // Executando exclusão do fornecedor e suas compras
        excluirFornecedorECompras(idFornecedorParaExcluir);
    }

    private static void excluirFornecedorECompras(int idFornecedor) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            // Desativa o modo de autocommit para controlar a transação manualmente
            connection.setAutoCommit(false);

            // Exclui as compras associadas ao fornecedor
            String sqlExcluirCompras = "DELETE FROM compras WHERE id_fornecedor = ?";
            try (PreparedStatement stmtExcluirCompras = connection.prepareStatement(sqlExcluirCompras)) {
                stmtExcluirCompras.setInt(1, idFornecedor);
                stmtExcluirCompras.executeUpdate();
            }

            // Exclui o fornecedor
            String sqlExcluirFornecedor = "DELETE FROM fornecedores WHERE id = ?";
            try (PreparedStatement stmtExcluirFornecedor = connection.prepareStatement(sqlExcluirFornecedor)) {
                stmtExcluirFornecedor.setInt(1, idFornecedor);
                stmtExcluirFornecedor.executeUpdate();
            }

            // Confirma as alterações na transação
            connection.commit();
            System.out.println("Fornecedor e suas compras excluídos com sucesso.");
        } catch (SQLException e) {
            // Em caso de erro, faz rollback na transação
            System.err.println("Erro ao excluir fornecedor e suas compras: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
