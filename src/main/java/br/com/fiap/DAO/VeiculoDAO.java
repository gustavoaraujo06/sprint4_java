package br.com.fiap.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Veiculo;

public class VeiculoDAO {

    public VeiculoDAO() {
    }

    public void create(Veiculo veiculo) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_VEICULO (VEICULO_ID, CHASSI, ANO, MARCA, MODELO, PLACA, PROPRIETARIO_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, veiculo.getId());
            stmt.setString(2, veiculo.getChassi());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getMarca());
            stmt.setString(5, veiculo.getModelo());
            stmt.setString(6, veiculo.getPlaca());
            stmt.setInt(7, veiculo.getProprietario().getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_VEICULO WHERE VEICULO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(int id, Veiculo veiculo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_VEICULO SET VEICULO_ID = ?, CHASSI = ?, ANO = ?, MARCA = ?, MODELO = ?, PLACA = ?, PROPRIETARIO_ID = ? WHERE VEICULO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, veiculo.getId());
            stmt.setString(2, veiculo.getChassi());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getMarca());
            stmt.setString(5, veiculo.getModelo());
            stmt.setString(6, veiculo.getPlaca());
            stmt.setInt(7, veiculo.getProprietario().getId());
            stmt.setInt(8, id);
            stmt.executeUpdate();
        }
    }

    public List<Veiculo> select() throws SQLException, ClassNotFoundException {
        List<Veiculo> veiculoList = new ArrayList<>();
        String sql = "SELECT * FROM T_VEICULO";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("VEICULO_ID"));
                veiculo.setChassi(rs.getString("CHASSI"));
                veiculo.setAno(rs.getInt("ANO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setPlaca(rs.getString("PLACA"));

                int proprietarioId = rs.getInt("PROPRIETARIO_ID");
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente proprietario = clienteDAO.select(proprietarioId);
                veiculo.setProprietario(proprietario);

                veiculoList.add(veiculo);
            }
        }
        return veiculoList;
    }

    public Veiculo select(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM T_VEICULO WHERE VEICULO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("VEICULO_ID"));
                    veiculo.setChassi(rs.getString("CHASSI"));
                    veiculo.setAno(rs.getInt("ANO"));
                    veiculo.setMarca(rs.getString("MARCA"));
                    veiculo.setModelo(rs.getString("MODELO"));
                    veiculo.setPlaca(rs.getString("PLACA"));

                    int proprietarioId = rs.getInt("PROPRIETARIO_ID");
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente proprietario = clienteDAO.select(proprietarioId);
                    veiculo.setProprietario(proprietario);

                    return veiculo;
                } else {
                    return null;
                }
            }
        }
    }
    public List<Veiculo> selectByOwner(int proprietarioId) throws SQLException, ClassNotFoundException {
    	List<Veiculo> veiculoList = new ArrayList<>();
        String sql = "SELECT * FROM T_VEICULO WHERE PROPRIETARIO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
    		stmt.setInt(1, proprietarioId);
    		ResultSet rs = stmt.executeQuery();

               while (rs.next()) {
                   Veiculo veiculo = new Veiculo();
                   veiculo.setId(rs.getInt("VEICULO_ID"));
                   veiculo.setChassi(rs.getString("CHASSI"));
                   veiculo.setAno(rs.getInt("ANO"));
                   veiculo.setMarca(rs.getString("MARCA"));
                   veiculo.setModelo(rs.getString("MODELO"));
                   veiculo.setPlaca(rs.getString("PLACA"));

                   ClienteDAO clienteDAO = new ClienteDAO();
                   Cliente proprietario = clienteDAO.select(proprietarioId);
                   veiculo.setProprietario(proprietario);

                   veiculoList.add(veiculo);
               }
           }
           return veiculoList;
    }

    public Boolean veiculoExiste(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_VEICULO WHERE VEICULO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
