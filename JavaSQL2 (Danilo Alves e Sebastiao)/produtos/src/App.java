//produtos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        // Configurações de conexão com o banco de dados
        String host = "localhost";
        String database = "exercicios2";
        String usuario = "root";
        String senha = "";
        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, usuario, senha)) {

            // Arrays de produtos e preços
            String[] nome = {
                "Camiseta de Algodão","Calça Jeans Skinny","Tênis Esportivo Nike","Vestido Floral","Óculos de Sol Ray-Ban","Relógio de Pulso Casio",
                "Mochila para Notebook","Bolsa de Couro Feminina","Boné Estilizado","Jaqueta Corta-Vento","Calça de Moletom","Sapato Social Masculino",
                "Biquíni Estampado","Shorts de Praia","Perfume Importado","Batom Matte","Camisa Polo Lacoste","Saia Midi Plissada","Tênis Converse Chuck Taylor",
                "Camisa de Flanela","Pulseira de Prata","Anel de Diamante","Colar de Pérolas","Blusa de Tricô","Chapéu Fedora","Bolsa Tote de Couro",
                "Blazer Slim Fit","Relógio Smartwatch","Calça Legging Esportiva","Camisa Social Branca","Bota de Couro","Vestido de Festa Longo","Mala de Viagem Grande",
                "Camisola de Seda","Sapato de Salto Alto","Blusa de Cetim","Sneaker Plataforma"
            };
            double[] preco = {
                19.99, 49.99, 89.99, 29.99, 129.99, 79.99, 39.99, 59.99, 14.99, 54.99,
                34.99, 69.99, 24.99, 19.99, 89.99, 9.99, 69.99, 39.99, 49.99, 29.99,
                19.99, 149.99, 99.99, 44.99, 24.99, 74.99, 64.99, 129.99, 29.99, 49.99,
                79.99, 129.99, 99.99, 34.99, 54.99, 29.99, 44.99
            };

            // Insere os produtos na tabela Estoque
            int linhasAfetadas = 0; // Variável para rastrear o número de linhas afetadas

            for (int i = 0; i < nome.length; i++) {
                String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);

                // Define os valores dos parâmetros na consulta SQL
                stmt.setString(1, nome[i]);
                stmt.setDouble(2, preco[i]);

                // Executa a consulta e adiciona o resultado ao contador de linhas afetadas
                linhasAfetadas += stmt.executeUpdate();
            }

            if (linhasAfetadas > 0) {
                System.out.println("Produtos adicionados com sucesso!"); // Imprime mensagem de sucesso
            } else {
                System.out.println("Erro ao inserir produtos."); // Imprime mensagem de erro em caso de falha
            }

            // Fecha a conexão com o banco de dados
            conexao.close();

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
