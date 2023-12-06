// Danilo Alves e Sebastiao

package com.atv27;

import java.sql.*;

public class atv27 {

    public static void main(String[] args) {
        // Informações do banco de dados
        String url = "jdbc:mysql://localhost:3306/atvjava2";
        String usuarioBanco = "root";
        String senhaBanco = "";

        // ID do produto que você deseja excluir
        int idProdutoParaExcluir = 3;

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuarioBanco, senhaBanco);

            // Desativa o autocommit para possibilitar a execução de transações
            conexao.setAutoCommit(false);

            try {
                // Remove as associações do produto com categorias
                String sqlRemoverAssociacoes = "DELETE FROM categorias_produtos WHERE id_produto = ?";
                try (PreparedStatement stmtRemoverAssociacoes = conexao.prepareStatement(sqlRemoverAssociacoes)) {
                    stmtRemoverAssociacoes.setInt(1, idProdutoParaExcluir);
                    stmtRemoverAssociacoes.executeUpdate();
                }

                // Exclui o produto
                String sqlExcluirProduto = "DELETE FROM produtos WHERE id = ?";
                try (PreparedStatement stmtExcluirProduto = conexao.prepareStatement(sqlExcluirProduto)) {
                    stmtExcluirProduto.setInt(1, idProdutoParaExcluir);
                    stmtExcluirProduto.executeUpdate();
                }

                // Se tudo ocorreu sem erros, commita a transação
                conexao.commit();
                System.out.println("Produto e associações excluídos com sucesso!");
            } catch (SQLException ex) {
                // Em caso de erro, faz rollback na transação
                conexao.rollback();
                System.err.println("Erro ao excluir produto e associações: " + ex.getMessage());
            } finally {
                // Fecha a conexão com o banco de dados
                conexao.close();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
