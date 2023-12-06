// Danilo Alves e Sebastiao

package com.atv30;

import java.sql.*;

public class atv30 {

    public static void main(String[] args) {
        // Informações do banco de dados
        String url = "jdbc:mysql://localhost:3306/atvjava2";
        String usuarioBanco = "root";
        String senhaBanco = "";

        // ID do projeto que você deseja excluir
        int idProjetoParaExcluir = 1;

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuarioBanco, senhaBanco);

            // Desativa o autocommit para possibilitar a execução de transações
            conexao.setAutoCommit(false);

            try {
                // Remove as atribuições vinculadas ao projeto
                String sqlRemoverAtribuicoes = "DELETE FROM atribuicoes WHERE id_projeto = ?";
                try (PreparedStatement stmtRemoverAtribuicoes = conexao.prepareStatement(sqlRemoverAtribuicoes)) {
                    stmtRemoverAtribuicoes.setInt(1, idProjetoParaExcluir);
                    stmtRemoverAtribuicoes.executeUpdate();
                }

                // Exclui o projeto
                String sqlExcluirProjeto = "DELETE FROM projetos WHERE id = ?";
                try (PreparedStatement stmtExcluirProjeto = conexao.prepareStatement(sqlExcluirProjeto)) {
                    stmtExcluirProjeto.setInt(1, idProjetoParaExcluir);
                    stmtExcluirProjeto.executeUpdate();
                }

                // Se tudo ocorreu sem erros, commita a transação
                conexao.commit();
                System.out.println("Projeto e atribuições excluídos com sucesso!");
            } catch (SQLException ex) {
                // Em caso de erro, faz rollback na transação
                conexao.rollback();
                System.err.println("Erro ao excluir projeto e atribuições: " + ex.getMessage());
            } finally {
                // Fecha a conexão com o banco de dados
                conexao.close();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
