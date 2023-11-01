// Feriados Nacionais
import java.sql.*;

public class App {
    public static void main(String[] args) {
        // Configurações de conexão com o banco de dados
        String host = "localhost";
        String database = "exercicios2";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, usuario, senha)) {
            // Definindo a data e descrição do feriado
            String data = "2023-09-07";
            String descricao = "Dia da Independência";

            // SQL para inserir a data e descrição do feriado na tabela feriados_nacionais
            String sql = "INSERT INTO feriados_nacionais (data, descricao) VALUES (?, ?)";

            // Preparação da declaração SQL para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Define os valores dos parâmetros na declaração SQL
            stmt.setString(1, data); // Define a data do feriado
            stmt.setString(2, descricao); // Define a descrição do feriado

            // Executa a inserção no banco de dados e verifica se alguma linha foi afetada
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida com base no número de linhas afetadas
            if (linhasAfetadas > 0) {
                System.out.println("Feriado adicionado com sucesso!"); // Imprime mensagem de sucesso
            } else {
                System.out.println("Erro ao inserir feriado."); // Imprime mensagem de erro em caso de falha
            }
            
        } catch (SQLException e) {
            // Captura exceções relacionadas à conexão com o banco de dados e imprime mensagem de erro
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}