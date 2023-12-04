import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {

    // Detalhes da conexão com o banco de dados
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/atvjava";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        // Dados de exemplo do cliente e venda
        String nomeCliente = "Cliente A";
        String emailCliente = "clienteA@example.com";
        String produtoVendido = "Produto X";
        double valorVenda = 99.99;

        // Inserir cliente e venda
        int idCliente = inserirCliente(nomeCliente, emailCliente);
        if (idCliente != -1) {
            inserirVenda(idCliente, produtoVendido, valorVenda);
            System.out.println("Cliente e venda registrados com sucesso!");
        } else {
            System.out.println("Erro ao registrar cliente.");
        }
    }

    private static int inserirCliente(String nomeCliente, String emailCliente) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO clientes (nome, email) VALUES (?, ?)";ö

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, nomeCliente);
                preparedStatement.setString(2, emailCliente);

                int linhasAfetadas = preparedStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    // Obter o ID gerado para o cliente
                    java.sql.ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private static void inserirVenda(int idCliente, String produtoVendido, double valorVenda) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO vendas (id_cliente, produto_vendido, valor) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idCliente);
                preparedStatement.setString(2, produtoVendido);
                preparedStatement.setDouble(3, valorVenda);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
