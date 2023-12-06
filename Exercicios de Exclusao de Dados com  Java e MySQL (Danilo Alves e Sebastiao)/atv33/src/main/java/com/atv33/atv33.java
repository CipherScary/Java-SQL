// Danilo ALves e Sebastiao

package com.atv33;

import java.sql.*;

public class atv33 {

    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/atvjava2";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static void main(String[] args) {
        // ID do livro que será removido
        int idLivroParaRemover = 2; // Substitua pelo ID desejado

        // Executando remoção do livro e suas associações com autores
        removerLivroEAutores(idLivroParaRemover);
    }

    private static void removerLivroEAutores(int idLivro) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            // Desativa o modo de autocommit para controlar a transação manualmente
            connection.setAutoCommit(false);

            // Remove as associações do livro com autores
            String sqlRemoverAssociacoes = "DELETE FROM autores_livros WHERE id_livro = ?";
            try (PreparedStatement stmtRemoverAssociacoes = connection.prepareStatement(sqlRemoverAssociacoes)) {
                stmtRemoverAssociacoes.setInt(1, idLivro);
                stmtRemoverAssociacoes.executeUpdate();
            }

            // Remove o livro
            String sqlRemoverLivro = "DELETE FROM livros WHERE id = ?";
            try (PreparedStatement stmtRemoverLivro = connection.prepareStatement(sqlRemoverLivro)) {
                stmtRemoverLivro.setInt(1, idLivro);
                stmtRemoverLivro.executeUpdate();
            }

            // Confirma as alterações na transação
            connection.commit();
            System.out.println("Livro e suas associações com autores removidos com sucesso.");
        } catch (SQLException e) {
            // Em caso de erro, faz rollback na transação
            System.err.println("Erro ao remover livro e suas associações com autores: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
