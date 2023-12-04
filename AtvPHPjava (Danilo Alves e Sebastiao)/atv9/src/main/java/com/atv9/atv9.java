// Danilo Alves e Sebastiao
package com.atv9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class atv9 {
    public static void main(String[] args) {
        // Dados do evento
        Evento evento = new Evento(1, "Nome do Evento", "2023-01-01");

        // Dados do participante
        Participante participante = new Participante(1, evento.getIdEvento(), "Nome do Participante");

        // Adicionar evento e participante ao banco de dados
        adicionarEventoEParticipante(evento, participante);
    }

    private static void adicionarEventoEParticipante(Evento evento, Participante participante) {
        String url = "jdbc:mysql://localhost:3306/atvjava";
        String usuario = "root";
        String senha = "";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // Inserir evento
            String queryEvento = "INSERT INTO eventos (id_evento, nome_evento, data) VALUES (?, ?, ?)";
            try (PreparedStatement pstEvento = conexao.prepareStatement(queryEvento)) {
                pstEvento.setInt(1, evento.getIdEvento());
                pstEvento.setString(2, evento.getNomeEvento());
                pstEvento.setString(3, evento.getData());
                pstEvento.executeUpdate();
            }

            // Inserir participante
            String queryParticipante = "INSERT INTO participantes (id_participante, id_evento, nome_participante) VALUES (?, ?, ?)";
            try (PreparedStatement pstParticipante = conexao.prepareStatement(queryParticipante)) {
                pstParticipante.setInt(1, participante.getIdParticipante());
                pstParticipante.setInt(2, participante.getIdEvento());
                pstParticipante.setString(3, participante.getNomeParticipante());
                pstParticipante.executeUpdate();
            }

            System.out.println("Evento e participante adicionados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Evento {
    private int idEvento;
    private String nomeEvento;
    private String data;

    // Construtor
    public Evento(int idEvento, String nomeEvento, String data) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.data = data;
    }

    // Getters e Setters
    public int getIdEvento() {
        return idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getData() {
        return data;
    }
}

class Participante {
    private int idParticipante;
    private int idEvento;
    private String nomeParticipante;

    // Construtor
    public Participante(int idParticipante, int idEvento, String nomeParticipante) {
        this.idParticipante = idParticipante;
        this.idEvento = idEvento;
        this.nomeParticipante = nomeParticipante;
    }

    // Getters e Setters
    public int getIdParticipante() {
        return idParticipante;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }
}
