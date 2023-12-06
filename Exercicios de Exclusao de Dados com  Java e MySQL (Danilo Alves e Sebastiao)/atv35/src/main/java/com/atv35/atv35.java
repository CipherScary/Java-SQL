package com.atv35;

import java.sql.*;

public class atv35 {

    private static final String URL = "jdbc:mysql://localhost:3306/atvjava2";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static void main(String[] args) {
        // ID do resultado de exame a ser removido
        int idResultadoExameParaRemover = 2;
        removerResultadoExameEPaciente(idResultadoExameParaRemover);
    }

    private static void removerResultadoExameEPaciente(int idResultadoExame) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            // Desativa o commit automático para transação manual
            connection.setAutoCommit(false);

            // Obtém o ID do paciente associado ao resultado de exame
            int idPacienteAssociado = obterIdPacienteAssociado(connection, idResultadoExame);

            // Remove os dados do paciente
            String sqlRemoverPaciente = "DELETE FROM pacientes WHERE id = ?";
            try (PreparedStatement stmtRemoverPaciente = connection.prepareStatement(sqlRemoverPaciente)) {
                stmtRemoverPaciente.setInt(1, idPacienteAssociado);
                stmtRemoverPaciente.executeUpdate();
            }

            // Remove o resultado de exame
            String sqlRemoverResultadoExame = "DELETE FROM resultados_exames WHERE id = ?";
            try (PreparedStatement stmtRemoverResultadoExame = connection.prepareStatement(sqlRemoverResultadoExame)) {
                stmtRemoverResultadoExame.setInt(1, idResultadoExame);
                stmtRemoverResultadoExame.executeUpdate();
            }

            // Realiza o commit da transação
            connection.commit();
            System.out.println("Resultado de exame e dados do paciente removidos com sucesso.");
        } catch (SQLException e) {
            // Em caso de erro, imprime a mensagem de erro
            System.err.println("Erro ao remover resultado de exame e dados do paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int obterIdPacienteAssociado(Connection connection, int idResultadoExame) throws SQLException {
        // Consulta SQL para obter o ID do paciente associado ao resultado de exame
        String sqlObterIdPaciente = "SELECT id_paciente FROM resultados_exames WHERE id = ?";
        try (PreparedStatement stmtObterIdPaciente = connection.prepareStatement(sqlObterIdPaciente)) {
            stmtObterIdPaciente.setInt(1, idResultadoExame);
            try (ResultSet resultSet = stmtObterIdPaciente.executeQuery()) {
                // Se houver um resultado, retorna o ID do paciente associado
                if (resultSet.next()) {
                    return resultSet.getInt("id_paciente");
                }
            }
        }
        // Lança uma exceção se o ID do paciente associado não for encontrado
        throw new SQLException("ID do paciente associado não encontrado para o resultado de exame.");
    }
}
