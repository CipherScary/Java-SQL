// Numeros Aleatorios
import java.sql.*;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        // Configurações de conexão com o banco de dados
        String host = "localhost";
        String database = "exercicios2";
        String usuario = "root";
        String senha = "";

        // Objeto para gerar números aleatórios
        Random random = new Random();
        
        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, usuario, senha)) {
            // Gera um número aleatório entre 0 e 99
            int numero = random.nextInt(100);

            // SQL para inserir o número aleatório na tabela numeros_aleatorios
            String sql = "INSERT INTO numeros_aleatorios (numero) VALUES (?)";

            // Preparação da declaração SQL para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Define o valor do parâmetro na declaração SQL como o número aleatório gerado
            stmt.setInt(1, numero);

            // Executa a inserção no banco de dados e verifica se alguma linha foi afetada
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida com base no número de linhas afetadas
            if (linhasAfetadas > 0) {
                System.out.println("Número adicionado com sucesso!");
            } else {
                System.out.println("Erro ao inserir número.");
            }
            
        } catch (SQLException e) {
            // Captura exceções relacionadas à conexão com o banco de dados
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}