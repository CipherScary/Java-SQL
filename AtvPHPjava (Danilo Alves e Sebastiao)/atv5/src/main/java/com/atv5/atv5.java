// Danilo Alves e Sebastiao
package com.atv5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class atv5 {
    public static void main(String[] args) {
        // Dados do projeto
        Projeto projeto = new Projeto(1, "Projeto XYZ", "Descrição do Projeto");

        // Dados da atribuição
        Atribuicao atribuicao = new Atribuicao(1, projeto.getIdProjeto(), 101); // Exemplo de ID de funcionário: 101

        // Adicionar projeto e atribuição ao banco de dados
        adicionarProjetoEAtribuicao(projeto, atribuicao);
    }

    private static void adicionarProjetoEAtribuicao(Projeto projeto, Atribuicao atribuicao) {
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // Inserir projeto
            String queryProjeto = "INSERT INTO projetos (id_projeto, nome_projeto, descricao) VALUES (?, ?, ?)";
            try (PreparedStatement pstProjeto = conexao.prepareStatement(queryProjeto)) {
                pstProjeto.setInt(1, projeto.getIdProjeto());
                pstProjeto.setString(2, projeto.getNomeProjeto());
                pstProjeto.setString(3, projeto.getDescricao());
                pstProjeto.executeUpdate();
            }

            // Inserir atribuição
            String queryAtribuicao = "INSERT INTO atribuicoes (id_atribuicao, id_projeto, id_funcionario) VALUES (?, ?, ?)";
            try (PreparedStatement pstAtribuicao = conexao.prepareStatement(queryAtribuicao)) {
                pstAtribuicao.setInt(1, atribuicao.getIdAtribuicao());
                pstAtribuicao.setInt(2, atribuicao.getIdProjeto());
                pstAtribuicao.setInt(3, atribuicao.getIdFuncionario());
                pstAtribuicao.executeUpdate();
            }

            System.out.println("Projeto e atribuição adicionados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Projeto {
    private int idProjeto;
    private String nomeProjeto;
    private String descricao;

    // Construtor
    public Projeto(int idProjeto, String nomeProjeto, String descricao) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getIdProjeto() {
        return idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }
}

class Atribuicao {
    private int idAtribuicao;
    private int idProjeto;
    private int idFuncionario;

    // Construtor
    public Atribuicao(int idAtribuicao, int idProjeto, int idFuncionario) {
        this.idAtribuicao = idAtribuicao;
        this.idProjeto = idProjeto;
        this.idFuncionario = idFuncionario;
    }

    // Getters e Setters
    public int getIdAtribuicao() {
        return idAtribuicao;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }
}
