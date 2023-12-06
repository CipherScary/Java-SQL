// Danilo Alves e Sebastiao

package com.atv26;

import java.sql.*;

public class atv26 {

    public static void main(String[] args) {
        // Informações do banco de dados
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuarioBanco = "root";
        String senhaBanco = "";

        // ID do usuário que você deseja excluir
        int idUsuarioParaExcluir = 1;

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuarioBanco, senhaBanco);

            // Desativa o autocommit para possibilitar a execução de transações
            conexao.setAutoCommit(false);

            try {
                // Exclui os pedidos associados ao usuário
                String sqlExcluirPedidos = "DELETE FROM pedidos WHERE id_usuario = ?";
                try (PreparedStatement stmtExcluirPedidos = conexao.prepareStatement(sqlExcluirPedidos)) {
                    stmtExcluirPedidos.setInt(1, idUsuarioParaExcluir);
                    stmtExcluirPedidos.executeUpdate();
                }

                // Exclui o usuário
                String sqlExcluirUsuario = "DELETE FROM usuarios WHERE id = ?";
                try (PreparedStatement stmtExcluirUsuario = conexao.prepareStatement(sqlExcluirUsuario)) {
                    stmtExcluirUsuario.setInt(1, idUsuarioParaExcluir);
                    stmtExcluirUsuario.executeUpdate();
                }

                // Se tudo ocorreu sem erros, commita a transação
                conexao.commit();
                System.out.println("Usuário e pedidos excluídos com sucesso!");
            } catch (SQLException ex) {
                // Em caso de erro, faz rollback na transação
                conexao.rollback();
                System.err.println("Erro ao excluir usuário e pedidos: " + ex.getMessage());
            } finally {
                // Fecha a conexão com o banco de dados
                conexao.close();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
