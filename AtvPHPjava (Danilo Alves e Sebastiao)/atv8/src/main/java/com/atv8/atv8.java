// Danilo Alves e Sebastiao
package com.atv8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class atv8 {
    public static void main(String[] args) {
        // Dados do livro
        Livro livro = new Livro(1, "Título do Livro", 2022);

        // Dados do autor
        Autor autor = new Autor(1, "Nome do Autor");

        // Adicionar livro e autor ao banco de dados
        adicionarLivroEAutor(livro, autor);
    }

    private static void adicionarLivroEAutor(Livro livro, Autor autor) {
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // Inserir autor
            String queryAutor = "INSERT INTO autores (id_autor, nome_autor) VALUES (?, ?)";
            try (PreparedStatement pstAutor = conexao.prepareStatement(queryAutor)) {
                pstAutor.setInt(1, autor.getIdAutor());
                pstAutor.setString(2, autor.getNomeAutor());
                pstAutor.executeUpdate();
            }

            // Inserir livro
            String queryLivro = "INSERT INTO livros (id_livro, titulo, ano_publicacao) VALUES (?, ?, ?)";
            try (PreparedStatement pstLivro = conexao.prepareStatement(queryLivro)) {
                pstLivro.setInt(1, livro.getIdLivro());
                pstLivro.setString(2, livro.getTitulo());
                pstLivro.setInt(3, livro.getAnoPublicacao());
                pstLivro.executeUpdate();
            }

            System.out.println("Livro e autor adicionados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Livro {
    private int idLivro;
    private String titulo;
    private int anoPublicacao;

    // Construtor
    public Livro(int idLivro, String titulo, int anoPublicacao) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    // Getters e Setters
    public int getIdLivro() {
        return idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }
}

class Autor {
    private int idAutor;
    private String nomeAutor;

    // Construtor
    public Autor(int idAutor, String nomeAutor) {
        this.idAutor = idAutor;
        this.nomeAutor = nomeAutor;
    }

    // Getters e Setters
    public int getIdAutor() {
        return idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
