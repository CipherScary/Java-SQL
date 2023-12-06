// Danilo Alves e Sebastiao

package com.atv28;

import java.sql.*;

public class atv28 {

    public static void main(String[] args) {
        // Informações do banco de dados
        String url = "jdbc:mysql://localhost:3306/atvjava2";
        String usuarioBanco = "root";
        String senhaBanco = "";

        // ID do cliente que você deseja excluir
        int idClienteParaExcluir = 1;

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuarioBanco, senhaBanco);

            // Desativa o autocommit para possibilitar a execução de transações
            conexao.setAutoCommit(false);

            try {
                // Remove os registros de vendas associados ao cliente
                String sqlRemoverVendas = "DELETE FROM vendas WHERE id_cliente = ?";
                try (PreparedStatement stmtRemoverVendas = conexao.prepareStatement(sqlRemoverVendas)) {
                    stmtRemoverVendas.setInt(1, idClienteParaExcluir);
                    stmtRemoverVendas.executeUpdate();
                }

                // Exclui o cliente
                String sqlExcluirCliente = "DELETE FROM clientes WHERE id = ?";
                try (PreparedStatement stmtExcluirCliente = conexao.prepareStatement(sqlExcluirCliente)) {
                    stmtExcluirCliente.setInt(1, idClienteParaExcluir);
                    stmtExcluirCliente.executeUpdate();
                }

                // Se tudo ocorreu sem erros, commita a transação
                conexao.commit();
                System.out.println("Cliente e vendas excluídos com sucesso!");
            } catch (SQLException ex) {
                // Em caso de erro, faz rollback na transação
                conexao.rollback();
                System.err.println("Erro ao excluir cliente e vendas: " + ex.getMessage());
            } finally {
                // Fecha a conexão com o banco de dados
                conexao.close();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
