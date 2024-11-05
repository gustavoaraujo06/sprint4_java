package br.com.fiap.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Oficina;

public class OficinaDAO {

    public OficinaDAO() {
    }

    public void create(Oficina oficina) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_OFICINA (OFICINA_ID, NOME, TELEFONE, EMAIL, CNPJ) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oficina.getId());
            stmt.setString(2, oficina.getNome());
            stmt.setString(3, oficina.getTelefone());
            stmt.setString(4, oficina.getEmail());
            stmt.setString(5, oficina.getCnpj());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_OFICINA WHERE OFICINA_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(int id, Oficina oficina) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_OFICINA SET OFICINA_ID = ?, NOME = ?, TELEFONE = ?, EMAIL = ?, CNPJ = ? WHERE OFICINA_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oficina.getId());
            stmt.setString(2, oficina.getNome());
            stmt.setString(3, oficina.getTelefone());
            stmt.setString(4, oficina.getEmail());
            stmt.setString(5, oficina.getCnpj());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    public List<Oficina> select() throws SQLException, ClassNotFoundException {
        List<Oficina> oficinaList = new ArrayList<>();
        String sql = "SELECT * FROM T_OFICINA";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Oficina oficina = new Oficina();
                oficina.setId(rs.getInt("OFICINA_ID"));
                oficina.setNome(rs.getString("NOME"));
                oficina.setTelefone(rs.getString("TELEFONE"));
                oficina.setEmail(rs.getString("EMAIL"));
                oficina.setCnpj(rs.getString("CNPJ"));
                oficinaList.add(oficina);
            }
        }
        return oficinaList;
    }

    public Oficina select(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM T_OFICINA WHERE OFICINA_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Oficina oficina = new Oficina();
                    oficina.setId(rs.getInt("OFICINA_ID"));
                    oficina.setNome(rs.getString("NOME"));
                    oficina.setTelefone(rs.getString("TELEFONE"));
                    oficina.setEmail(rs.getString("EMAIL"));
                    oficina.setCnpj(rs.getString("CNPJ"));
                    return oficina;
                } else {
                    return null;
                }
            }
        }
    }

    public Boolean oficinaExiste(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_OFICINA WHERE OFICINA_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
