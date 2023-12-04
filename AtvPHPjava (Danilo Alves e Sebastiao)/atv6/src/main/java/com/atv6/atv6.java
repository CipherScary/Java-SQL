package com.atv6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class atv6 {
    public static void main(String[] args) {
        // Dados do aluno
        Aluno aluno = new Aluno(1, "Nome do Aluno", "Turma A");

        // Dados do curso
        Curso curso = new Curso(1, "Curso de Java", "Instrutor Java");

        // Adicionar aluno e curso ao banco de dados
        adicionarAlunoECurso(aluno, curso);
    }

    private static void adicionarAlunoECurso(Aluno aluno, Curso curso) {
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // Inserir aluno
            String queryAluno = "INSERT INTO alunos (id_aluno, nome, turma) VALUES (?, ?, ?)";
            try (PreparedStatement pstAluno = conexao.prepareStatement(queryAluno)) {
                pstAluno.setInt(1, aluno.getIdAluno());
                pstAluno.setString(2, aluno.getNome());
                pstAluno.setString(3, aluno.getTurma());
                pstAluno.executeUpdate();
            }

            // Inserir curso
            String queryCurso = "INSERT INTO cursos (id_curso, nome_curso, instrutor) VALUES (?, ?, ?)";
            try (PreparedStatement pstCurso = conexao.prepareStatement(queryCurso)) {
                pstCurso.setInt(1, curso.getIdCurso());
                pstCurso.setString(2, curso.getNomeCurso());
                pstCurso.setString(3, curso.getInstrutor());
                pstCurso.executeUpdate();
            }

            System.out.println("Aluno e curso adicionados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Aluno {
    private int idAluno;
    private String nome;
    private String turma;

    // Construtor
    public Aluno(int idAluno, String nome, String turma) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.turma = turma;
    }

    // Getters e Setters
    public int getIdAluno() {
        return idAluno;
    }

    public String getNome() {
        return nome;
    }

    public String getTurma() {
        return turma;
    }
}

class Curso {
    private int idCurso;
    private String nomeCurso;
    private String instrutor;

    // Construtor
    public Curso(int idCurso, String nomeCurso, String instrutor) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.instrutor = instrutor;
    }

    // Getters e Setters
    public int getIdCurso() {
        return idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public String getInstrutor() {
        return instrutor;
    }
}
