package com.atv7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class atv7 {
    public static void main(String[] args) {
        // Dados do fornecedor
        Fornecedor fornecedor = new Fornecedor(1, "Nome do Fornecedor", "Contato do Fornecedor");

        // Dados da compra
        Compra compra = new Compra(1, fornecedor.getIdFornecedor(), "Produto A", 10);

        // Adicionar fornecedor e compra ao banco de dados
        adicionarFornecedorECompra(fornecedor, compra);
    }

    private static void adicionarFornecedorECompra(Fornecedor fornecedor, Compra compra) {
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // Inserir fornecedor
            String queryFornecedor = "INSERT INTO fornecedores (id_fornecedor, nome, contato) VALUES (?, ?, ?)";
            try (PreparedStatement pstFornecedor = conexao.prepareStatement(queryFornecedor)) {
                pstFornecedor.setInt(1, fornecedor.getIdFornecedor());
                pstFornecedor.setString(2, fornecedor.getNome());
                pstFornecedor.setString(3, fornecedor.getContato());
                pstFornecedor.executeUpdate();
            }

            // Inserir compra
            String queryCompra = "INSERT INTO compras (id_compra, id_fornecedor, produto_comprado, quantidade) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstCompra = conexao.prepareStatement(queryCompra)) {
                pstCompra.setInt(1, compra.getIdCompra());
                pstCompra.setInt(2, compra.getIdFornecedor());
                pstCompra.setString(3, compra.getProdutoComprado());
                pstCompra.setInt(4, compra.getQuantidade());
                pstCompra.executeUpdate();
            }

            System.out.println("Fornecedor e compra adicionados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Fornecedor {
    private int idFornecedor;
    private String nome;
    private String contato;

    // Construtor
    public Fornecedor(int idFornecedor, String nome, String contato) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.contato = contato;
    }

    // Getters e Setters
    public int getIdFornecedor() {
        return idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }
}

class Compra {
    private int idCompra;
    private int idFornecedor;
    private String produtoComprado;
    private int quantidade;

    // Construtor
    public Compra(int idCompra, int idFornecedor, String produtoComprado, int quantidade) {
        this.idCompra = idCompra;
        this.idFornecedor = idFornecedor;
        this.produtoComprado = produtoComprado;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getIdCompra() {
        return idCompra;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public String getProdutoComprado() {
        return produtoComprado;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
