// Cotacao Moeda
import java.sql.*;

public class App {
    public static void main(String[] args) {
        // Configurações de conexão com o banco de dados
        String host = "localhost";
        String database = "exercicios2";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, usuario, senha)) {
            // Definindo a moeda e valor da cotação
            String moeda = "Dolar";
            Double valor = 5.00;

            // SQL para inserir a moeda e valor da cotação na tabela cotacao_moeda
            String sql = "INSERT INTO cotacao_moeda (moeda, valor) VALUES (?, ?)";

            // Preparação da declaração SQL para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Define os valores dos parâmetros na declaração SQL
            stmt.setString(1, moeda); // Define o nome da moeda
            stmt.setDouble(2, valor); // Define o valor da cotação

            // Executa a inserção no banco de dados e verifica se alguma linha foi afetada
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida com base no número de linhas afetadas
            if (linhasAfetadas > 0) {
                System.out.println("Produto adicionado com sucesso!"); // Imprime mensagem de sucesso
            } else {
                System.out.println("Erro ao inserir moeda."); // Imprime mensagem de erro em caso de falha
            }
        } catch (SQLException e) {
            // Captura exceções relacionadas à conexão com o banco de dados e imprime mensagem de erro
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}