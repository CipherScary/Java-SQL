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
        // Dados de exemplo do funcionário e departamento
        String nomeFuncionario = "Funcionário A";
        String cargoFuncionario = "Desenvolvedor";
        String nomeDepartamento = "Departamento de TI";

        // Inserir funcionário e departamento
        int idFuncionario = inserirFuncionario(nomeFuncionario, cargoFuncionario);
        if (idFuncionario != -1) {
            int idDepartamento = inserirDepartamento(nomeDepartamento);
            if (idDepartamento != -1) {
                associarFuncionarioDepartamento(idFuncionario, idDepartamento);
                System.out.println("Funcionário e departamento registrados com sucesso!");
            } else {
                System.out.println("Erro ao registrar departamento.");
            }
        } else {
            System.out.println("Erro ao registrar funcionário.");
        }
    }

    private static int inserirFuncionario(String nomeFuncionario, String cargoFuncionario) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO funcionarios (nome, cargo) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, nomeFuncionario);
                preparedStatement.setString(2, cargoFuncionario);

                int linhasAfetadas = preparedStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    // Obter o ID gerado para o funcionário
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

    private static int inserirDepartamento(String nomeDepartamento) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO departamentos (nome_departamento) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, nomeDepartamento);

                int linhasAfetadas = preparedStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    // Obter o ID gerado para o departamento
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

    private static void associarFuncionarioDepartamento(int idFuncionario, int idDepartamento) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO funcionarios_departamentos (id_funcionario, id_departamento) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idFuncionario);
                preparedStatement.setInt(2, idDepartamento);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
