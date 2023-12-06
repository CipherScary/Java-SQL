// Danilo Alves e Sebastiao

package com.atv29;

import java.sql.*;

public class atv29 {

    public static void main(String[] args) {
        // Informações do banco de dados
        String url = "jdbc:mysql://localhost:3306/atvjava2";
        String usuarioBanco = "root";
        String senhaBanco = "";

        // ID do funcionário que você deseja excluir
        int idFuncionarioParaExcluir = 2;

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuarioBanco, senhaBanco);

            // Desativa o autocommit para possibilitar a execução de transações
            conexao.setAutoCommit(false);

            try {
                // Remove as associações do funcionário com departamentos
                String sqlRemoverAssociacoes = "DELETE FROM funcionarios_departamentos WHERE id_funcionario = ?";
                try (PreparedStatement stmtRemoverAssociacoes = conexao.prepareStatement(sqlRemoverAssociacoes)) {
                    stmtRemoverAssociacoes.setInt(1, idFuncionarioParaExcluir);
                    stmtRemoverAssociacoes.executeUpdate();
                }

                // Exclui o funcionário
                String sqlExcluirFuncionario = "DELETE FROM funcionarios WHERE id = ?";
                try (PreparedStatement stmtExcluirFuncionario = conexao.prepareStatement(sqlExcluirFuncionario)) {
                    stmtExcluirFuncionario.setInt(1, idFuncionarioParaExcluir);
                    stmtExcluirFuncionario.executeUpdate();
                }

                // Se tudo ocorreu sem erros, commita a transação
                conexao.commit();
                System.out.println("Funcionário e associações excluídos com sucesso!");
            } catch (SQLException ex) {
                // Em caso de erro, faz rollback na transação
                conexao.rollback();
                System.err.println("Erro ao excluir funcionário e associações: " + ex.getMessage());
            } finally {
                // Fecha a conexão com o banco de dados
                conexao.close();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
