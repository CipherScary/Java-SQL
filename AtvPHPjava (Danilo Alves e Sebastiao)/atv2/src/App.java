// Danilo Alves e Sebastiao
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
        // Dados de exemplo do produto e categoria
        String nomeProduto = "Produto A";
        double precoProduto = 29.99;
        String nomeCategoria = "Categoria 1";

        // Adicionar produto e categoria
        int idProduto = adicionarProduto(nomeProduto, precoProduto);
        if (idProduto != -1) {
            int idCategoria = adicionarCategoria(nomeCategoria);
            if (idCategoria != -1) {
                associarProdutoCategoria(idProduto, idCategoria);
                System.out.println("Produto e categoria adicionados com sucesso!");
            } else {
                System.out.println("Erro ao adicionar categoria.");
            }
        } else {
            System.out.println("Erro ao adicionar produto.");
        }
    }

    private static int adicionarProduto(String nomeProduto, double precoProduto) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO produtos (nome_produto, preco) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, nomeProduto);
                preparedStatement.setDouble(2, precoProduto);

                int linhasAfetadas = preparedStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    // Obter o ID gerado para o produto
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

    private static int adicionarCategoria(String nomeCategoria) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO categorias (nome_categoria) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, nomeCategoria);

                int linhasAfetadas = preparedStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    // Obter o ID gerado para a categoria
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

    private static void associarProdutoCategoria(int idProduto, int idCategoria) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO produtos_categorias (id_produto, id_categoria) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idProduto);
                preparedStatement.setInt(2, idCategoria);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
