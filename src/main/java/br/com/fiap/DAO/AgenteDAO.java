package br.com.fiap.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Agente;

public class AgenteDAO {

    public AgenteDAO() {
    	
    }

    public void create(Agente agente) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_AGENTE (AGENTE_ID, NOME, CPF, RG, EMAIL) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, agente.getId());
            stmt.setString(2, agente.getNome());
            stmt.setString(3, agente.getCpf());
            stmt.setString(4, agente.getRg());
            stmt.setString(5, agente.getEmail());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_AGENTE WHERE AGENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(int id, Agente agente) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_AGENTE SET AGENTE_ID = ?, NOME = ?, CPF = ?, RG = ?, EMAIL = ? WHERE AGENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, agente.getId());
            stmt.setString(2, agente.getNome());
            stmt.setString(3, agente.getCpf());
            stmt.setString(4, agente.getRg());
            stmt.setString(5, agente.getEmail());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    public List<Agente> select() throws SQLException, ClassNotFoundException {
        List<Agente> agenteList = new ArrayList<>();
        String sql = "SELECT * FROM T_AGENTE";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Agente agente = new Agente();
                agente.setId(rs.getInt("AGENTE_ID"));
                agente.setNome(rs.getString("NOME"));
                agente.setCpf(rs.getString("CPF"));
                agente.setRg(rs.getString("RG"));
                agente.setEmail(rs.getString("EMAIL"));
                agenteList.add(agente);
            }
        }
        return agenteList;
    }

    public Agente select(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM T_AGENTE WHERE AGENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Agente agente = new Agente();
                    agente.setId(rs.getInt("AGENTE_ID"));
                    agente.setNome(rs.getString("NOME"));
                    agente.setCpf(rs.getString("CPF"));
                    agente.setRg(rs.getString("RG"));
                    agente.setEmail(rs.getString("EMAIL"));
                    return agente;
                } else {
                    return null; 
                }
            }
        }
    }

    public Boolean agenteExiste(Agente agente) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_AGENTE WHERE AGENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, agente.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                } else {
                    return false;
                }
            }
        }
    }

    public Boolean agenteExiste(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_AGENTE WHERE AGENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                } else {
                    return false;
                }
            }
        }
    }
}
