package br.com.fiap.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Diagnostico;

public class DiagnosticoDAO {

    public DiagnosticoDAO() {
    }

    public void create(Diagnostico diagnostico) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_DIAGNOSTICO (DIAGNOSTICO_ID, DESCRICAO) VALUES (?, ?)";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, diagnostico.getId());
            stmt.setString(2, diagnostico.getDescricao());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_DIAGNOSTICO WHERE DIAGNOSTICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(int id, Diagnostico diagnostico) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_DIAGNOSTICO SET DIAGNOSTICO_ID = ?, DESCRICAO = ? WHERE DIAGNOSTICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, diagnostico.getId());
            stmt.setString(2, diagnostico.getDescricao());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    public List<Diagnostico> select() throws SQLException, ClassNotFoundException {
        List<Diagnostico> diagnosticoList = new ArrayList<>();
        String sql = "SELECT * FROM T_DIAGNOSTICO";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setId(rs.getInt("DIAGNOSTICO_ID"));
                diagnostico.setDescricao(rs.getString("DESCRICAO"));
                diagnosticoList.add(diagnostico);
            }
        }
        return diagnosticoList;
    }

    public Diagnostico select(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM T_DIAGNOSTICO WHERE DIAGNOSTICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Diagnostico diagnostico = new Diagnostico();
                    diagnostico.setId(rs.getInt("DIAGNOSTICO_ID"));
                    diagnostico.setDescricao(rs.getString("DESCRICAO"));
                    return diagnostico;
                } else {
                    return null;
                }
            }
        }
    }

    public Boolean diagnosticoExiste(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_DIAGNOSTICO WHERE DIAGNOSTICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
