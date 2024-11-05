package br.com.fiap.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Oficina;
import br.com.fiap.model.Servico;

public class ServicoDAO {

    public ServicoDAO() {
    }

    public void create(Servico servico) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_SERVICO (SERVICO_ID, DESCRICAO, VALOR, DURACAO, DATA_DE_CRIACAO, OFICINA_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, servico.getId());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            stmt.setInt(4, servico.getDuracao());
            stmt.setDate(5, Date.valueOf(servico.getDataCriacao()));
            stmt.setInt(6, servico.getOficina().getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_SERVICO WHERE SERVICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(int id, Servico servico) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_SERVICO SET SERVICO_ID = ?, DESCRICAO = ?, VALOR = ?, DURACAO = ?, DATA_DE_CRIACAO = ?, OFICINA_ID = ? WHERE SERVICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, servico.getId());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            stmt.setInt(4, servico.getDuracao());
            stmt.setDate(5, Date.valueOf(servico.getDataCriacao()));
            stmt.setInt(6, servico.getOficina().getId());
            stmt.setInt(7, id);
            stmt.executeUpdate();
        }
    }

    public List<Servico> select() throws SQLException, ClassNotFoundException {
        List<Servico> servicoList = new ArrayList<>();
        String sql = "SELECT * FROM T_SERVICO";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getInt("SERVICO_ID"));
                servico.setDescricao(rs.getString("DESCRICAO"));
                servico.setValor(rs.getDouble("VALOR"));
                servico.setDuracao(rs.getInt("DURACAO"));
                servico.setDataCriacao(rs.getDate("DATA_DE_CRIACAO").toLocalDate());

                int oficinaId = rs.getInt("OFICINA_ID");
                OficinaDAO oficinaDAO = new OficinaDAO();
                Oficina oficina = oficinaDAO.select(oficinaId);
                servico.setOficina(oficina);

                servicoList.add(servico);
            }
        }
        return servicoList;
    }

    public Servico select(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM T_SERVICO WHERE SERVICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Servico servico = new Servico();
                    servico.setId(rs.getInt("SERVICO_ID"));
                    servico.setDescricao(rs.getString("DESCRICAO"));
                    servico.setValor(rs.getDouble("VALOR"));
                    servico.setDuracao(rs.getInt("DURACAO"));
                    servico.setDataCriacao(rs.getDate("DATA_DE_CRIACAO").toLocalDate());

                    int oficinaId = rs.getInt("OFICINA_ID");
                    OficinaDAO oficinaDAO = new OficinaDAO();
                    Oficina oficina = oficinaDAO.select(oficinaId);
                    servico.setOficina(oficina);

                    return servico;
                } else {
                    return null;
                }
            }
        }
    }

    public Boolean servicoExiste(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_SERVICO WHERE SERVICO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
