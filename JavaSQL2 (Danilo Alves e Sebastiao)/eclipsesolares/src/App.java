// Eclipse Solares
import java.sql.*;

public class App {
    public static void main(String[] args) {
        // Configurações de conexão com o banco de dados
        String host = "localhost";
        String database = "exercicios2";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, usuario, senha)) {
            // Definindo a data e o tipo do eclipse
            String data = "2024-10-02";
            String tipo = "Anular";

            // SQL para inserir a data e o tipo do eclipse na tabela eclipses_solares
            String sql = "INSERT INTO eclipses_solares (data, tipo) VALUES (?, ?)";

            // Preparação da declaração SQL para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Define os valores dos parâmetros na declaração SQL
            stmt.setString(1, data); // Define a data do eclipse
            stmt.setString(2, tipo); // Define o tipo do eclipse

            // Executa a inserção no banco de dados e verifica se alguma linha foi afetada
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida com base no número de linhas afetadas
            if (linhasAfetadas > 0) {
                System.out.println("Eclipse adicionado com sucesso!"); // Imprime mensagem de sucesso
            } else {
                System.out.println("Erro ao inserir eclipse."); // Imprime mensagem de erro em caso de falha
            }
        } catch (SQLException e) {
            // Captura exceções relacionadas à conexão com o banco de dados e imprime mensagem de erro
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}