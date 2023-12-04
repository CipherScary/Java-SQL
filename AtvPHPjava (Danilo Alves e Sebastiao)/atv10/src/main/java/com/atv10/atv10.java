package com.atv10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class atv10 {
    public static void main(String[] args) {
        // Dados do resultado de exame
        ResultadoExame resultadoExame = new ResultadoExame(1, "Hemograma", "Normal");

        // Dados do paciente
        Date dataNascimento = null;
        try {
            dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Paciente paciente = new Paciente(1, "Nome do Paciente", dataNascimento);

        // Adicionar resultado de exame e paciente ao banco de dados
        adicionarResultadoExameEPaciente(resultadoExame, paciente);
    }

    private static void adicionarResultadoExameEPaciente(ResultadoExame resultadoExame, Paciente paciente) {
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // Inserir resultado de exame
            String queryResultadoExame = "INSERT INTO resultados_exames (id_resultado, tipo_exame, resultado) VALUES (?, ?, ?)";
            try (PreparedStatement pstResultadoExame = conexao.prepareStatement(queryResultadoExame)) {
                pstResultadoExame.setInt(1, resultadoExame.getIdResultado());
                pstResultadoExame.setString(2, resultadoExame.getTipoExame());
                pstResultadoExame.setString(3, resultadoExame.getResultado());
                pstResultadoExame.executeUpdate();
            }

            // Inserir paciente
            String queryPaciente = "INSERT INTO pacientes (id_paciente, nome_paciente, data_nascimento) VALUES (?, ?, ?)";
            try (PreparedStatement pstPaciente = conexao.prepareStatement(queryPaciente)) {
                pstPaciente.setInt(1, paciente.getIdPaciente());
                pstPaciente.setString(2, paciente.getNomePaciente());
                pstPaciente.setDate(3, new java.sql.Date(paciente.getDataNascimento().getTime()));
                pstPaciente.executeUpdate();
            }

            System.out.println("Resultado de exame e paciente adicionados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class ResultadoExame {
    private int idResultado;
    private String tipoExame;
    private String resultado;

    // Construtor
    public ResultadoExame(int idResultado, String tipoExame, String resultado) {
        this.idResultado = idResultado;
        this.tipoExame = tipoExame;
        this.resultado = resultado;
    }

    // Getters e Setters
    public int getIdResultado() {
        return idResultado;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public String getResultado() {
        return resultado;
    }
}

class Paciente {
    private int idPaciente;
    private String nomePaciente;
    private Date dataNascimento;

    // Construtor
    public Paciente(int idPaciente, String nomePaciente, Date dataNascimento) {
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getIdPaciente() {
        return idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
}
