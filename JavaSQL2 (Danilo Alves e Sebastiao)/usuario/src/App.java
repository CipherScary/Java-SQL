// Cadastro Usuario
import java.sql.*;

public class App {
    public static void main(String[] args) {
        // Configurações de conexão com o banco de dados
        String host = "localhost";
        String database = "exercicios2";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, usuario, senha)) {
            // Definindo os dados do usuário
            String nome = "Ludmilo Gasolino da Silva";
            String email = "Ludmilocnpjoto@gmail.com";
            String telefone = "(34) 9123456780";
            
            // SQL para inserir o usuário na tabela cadastro_usuario
            String sql = "INSERT INTO cadastro_usuario (nome, email, telefone) VALUES (?, ?, ?)";

            // Preparação da declaração SQL para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Define os valores dos parâmetros na declaração SQL
            stmt.setString(1, nome); // Define o nome do usuário
            stmt.setString(2, email); // Define o email do usuário
            stmt.setString(3, telefone); // Define o telefone do usuário

            // Executa a inserção no banco de dados e verifica se alguma linha foi afetada
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida com base no número de linhas afetadas
            if (linhasAfetadas > 0) {
                System.out.println("Usuário adicionado com sucesso!"); // Imprime mensagem de sucesso
            } else {
                System.out.println("Erro ao inserir usuário."); // Imprime mensagem de erro em caso de falha
            }
        } catch (SQLException e) {
            // Captura exceções relacionadas à conexão com o banco de dados e imprime mensagem de erro
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}