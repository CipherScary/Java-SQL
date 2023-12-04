import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuario = "root";
        String senha = "";

        // Dados de exemplo de usuário e pedido
        String userName = "John Doe";
        String userEmail = "john.doe@example.com";
        String product = "Sample Product";
        int quantity = 3;

        // Inserir usuário e pedido
        int userId = insertUser(userName, userEmail, url, usuario, senha);
        if (userId != -1) {
            insertOrder(userId, product, quantity, url, usuario, senha);
            System.out.println("Usuário e pedido registrados com sucesso!");
        } else {
            System.out.println("Erro ao registrar usuário.");
        }
    }

    // Método para inserir um novo usuário no banco de dados
    private static int insertUser(String name, String email, String url, String usuario, String senha) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String insertUserQuery = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Recupera o ID do usuário gerado automaticamente
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            return generatedKeys.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Método para inserir um novo pedido no banco de dados
    private static void insertOrder(int userId, String product, int quantity, String url, String usuario, String senha) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String insertOrderQuery = "INSERT INTO pedidos (id_usuario, produto, quantidade) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderQuery)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, product);
                preparedStatement.setInt(3, quantity);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
