// Danilo ALves e Sebastiao

package com.atv34;

import java.sql.*;

public class atv34 {

    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/atvjava2";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static void main(String[] args) {
        // ID do evento que será excluído
        int idEventoParaExcluir = 2; // Substitua pelo ID desejado

        // Executando exclusão do evento e seus participantes
        excluirEventoEParticipantes(idEventoParaExcluir);
    }

    private static void excluirEventoEParticipantes(int idEvento) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            // Desativa o modo de autocommit para controlar a transação manualmente
            connection.setAutoCommit(false);

            // Remove os participantes associados ao evento
            String sqlRemoverParticipantes = "DELETE FROM participantes WHERE id_evento = ?";
            try (PreparedStatement stmtRemoverParticipantes = connection.prepareStatement(sqlRemoverParticipantes)) {
                stmtRemoverParticipantes.setInt(1, idEvento);
                stmtRemoverParticipantes.executeUpdate();
            }

            // Remove o evento
            String sqlRemoverEvento = "DELETE FROM eventos WHERE id = ?";
            try (PreparedStatement stmtRemoverEvento = connection.prepareStatement(sqlRemoverEvento)) {
                stmtRemoverEvento.setInt(1, idEvento);
                stmtRemoverEvento.executeUpdate();
            }

            // Confirma as alterações na transação
            connection.commit();
            System.out.println("Evento e seus participantes excluídos com sucesso.");
        } catch (SQLException e) {
            // Em caso de erro, faz rollback na transação
            System.err.println("Erro ao excluir evento e seus participantes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
