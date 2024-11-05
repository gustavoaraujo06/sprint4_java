package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Cliente;

public class ClienteDAO {

    public ClienteDAO() {
    	
    }

    public void create(Cliente cliente) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_CLIENTE (CLIENTE_ID, NOME, CPF, RG, CONFIABILIDADE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.setInt(5, cliente.getConfiabilidade());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_CLIENTE WHERE CLIENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println(id);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(int id, Cliente cliente) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_CLIENTE SET CLIENTE_ID = ?, NOME = ?, CPF = ?, RG = ?, CONFIABILIDADE = ? WHERE CLIENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.setInt(5, cliente.getConfiabilidade());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    public List<Cliente> select() throws SQLException, ClassNotFoundException {
        List<Cliente> clientList = new ArrayList<>();
        String sql = "SELECT * FROM T_CLIENTE";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLIENTE_ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setRg(rs.getString("RG"));
                cliente.setConfiabilidade(rs.getInt("CONFIABILIDADE"));
                clientList.add(cliente);
            }
        }
        return clientList;
    }

    public Cliente select(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM T_CLIENTE WHERE CLIENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("CLIENTE_ID"));
                    cliente.setNome(rs.getString("NOME"));
                    cliente.setCpf(rs.getString("CPF"));
                    cliente.setRg(rs.getString("RG"));
                    cliente.setConfiabilidade(rs.getInt("CONFIABILIDADE"));
                    return cliente;
                } else {
                    return null;
                }
            }
        }
    }

    public Boolean clienteExiste(Cliente cliente) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_CLIENTE WHERE CLIENTE_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cliente.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                } else {
                    return false;
                }
            }
        }
    }

    public Boolean clienteExiste(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_CLIENTE WHERE CLIENTE_ID = ?";
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
