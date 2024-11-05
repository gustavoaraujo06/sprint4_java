package br.com.fiap.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Agente;
import br.com.fiap.model.Diagnostico;
import br.com.fiap.model.Processo;
import br.com.fiap.model.Servico;
import br.com.fiap.model.Veiculo;

public class ProcessoDAO {

    public ProcessoDAO() {
    }

    public void create(Processo processo) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_PROCESSO (PROCESSO_ID, STATUS, DESCRICAO, PRIORIDADE, ESTIMATIVA_CONCLUSAO, VEICULO_ID, DIAGNOSTICO_ID, RESPONSAVEL_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = new ConnectionFactory().connect();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, processo.getId());
                stmt.setString(2, String.valueOf(processo.getStatus()));
                stmt.setString(3, processo.getDescricao());
                stmt.setInt(4, processo.getPrioridade());
                if (processo.getEstimativaConclusao() != null) {
                    stmt.setDate(5, Date.valueOf(processo.getEstimativaConclusao()));
                } else {
                    stmt.setNull(5, Types.DATE);
                }
                stmt.setInt(6, processo.getVeiculo().getId());
                stmt.setInt(7, processo.getDiagnostico().getId());
                stmt.setInt(8, processo.getResponsavel().getId());
                stmt.executeUpdate();
            }

            if (processo.getServicos() != null && processo.getServicos().length > 0) {
                insertProcessoServicos(conn, processo.getId(), processo.getServicos());
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = new ConnectionFactory().connect();
            conn.setAutoCommit(false);

            String sqlDeleteServicos = "DELETE FROM T_PROCESSO_SERVICO WHERE PROCESSO_ID = ?";
            try (PreparedStatement stmtDeleteServicos = conn.prepareStatement(sqlDeleteServicos)) {
                stmtDeleteServicos.setInt(1, id);
                stmtDeleteServicos.executeUpdate();
            }

            String sql = "DELETE FROM T_PROCESSO WHERE PROCESSO_ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void update(int id, Processo processo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_PROCESSO SET PROCESSO_ID = ?, STATUS = ?, DESCRICAO = ?, PRIORIDADE = ?, ESTIMATIVA_CONCLUSAO = ?, VEICULO_ID = ?, DIAGNOSTICO_ID = ?, RESPONSAVEL_ID = ? WHERE PROCESSO_ID = ?";
        Connection conn = null;
        try {
            conn = new ConnectionFactory().connect();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, processo.getId());
                stmt.setString(2, String.valueOf(processo.getStatus()));
                stmt.setString(3, processo.getDescricao());
                stmt.setInt(4, processo.getPrioridade());
                if (processo.getEstimativaConclusao() != null) {
                    stmt.setDate(5, Date.valueOf(processo.getEstimativaConclusao()));
                } else {
                    stmt.setNull(5, Types.DATE);
                }
                stmt.setInt(6, processo.getVeiculo().getId());
                stmt.setInt(7, processo.getDiagnostico().getId());
                stmt.setInt(8, processo.getResponsavel().getId());
                stmt.setInt(9, id);
                stmt.executeUpdate();
            }

            String sqlDeleteServicos = "DELETE FROM T_PROCESSO_SERVICO WHERE PROCESSO_ID = ?";
            try (PreparedStatement stmtDeleteServicos = conn.prepareStatement(sqlDeleteServicos)) {
                stmtDeleteServicos.setInt(1, id);
                stmtDeleteServicos.executeUpdate();
            }

            if (processo.getServicos() != null && processo.getServicos().length > 0) {
                insertProcessoServicos(conn, id, processo.getServicos());
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Processo> select() throws SQLException, ClassNotFoundException {
        List<Processo> processoList = new ArrayList<>();
        String sql = "SELECT * FROM T_PROCESSO";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Processo processo = extractProcessoFromResultSet(rs);
                processoList.add(processo);
            }
        }
        return processoList;
    }

    public Processo select(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM T_PROCESSO WHERE PROCESSO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractProcessoFromResultSet(rs);
                } else {
                    return null;
                }
            }
        }
    }

    public Boolean processoExiste(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM T_PROCESSO WHERE PROCESSO_ID = ?";
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private Processo extractProcessoFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        Processo processo = new Processo();
        processo.setId(rs.getInt("PROCESSO_ID"));
        processo.setStatus(rs.getString("STATUS").charAt(0));
        processo.setDescricao(rs.getString("DESCRICAO"));
        processo.setPrioridade(rs.getInt("PRIORIDADE"));
        Date estimativaDate = rs.getDate("ESTIMATIVA_CONCLUSAO");
        if (estimativaDate != null) {
            processo.setEstimativaConclusao(estimativaDate.toLocalDate());
        }

        int veiculoId = rs.getInt("VEICULO_ID");
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = veiculoDAO.select(veiculoId);
        processo.setVeiculo(veiculo);

        int diagnosticoId = rs.getInt("DIAGNOSTICO_ID");
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
        Diagnostico diagnostico = diagnosticoDAO.select(diagnosticoId);
        processo.setDiagnostico(diagnostico);

        int responsavelId = rs.getInt("RESPONSAVEL_ID");
        AgenteDAO agenteDAO = new AgenteDAO();
        Agente responsavel = agenteDAO.select(responsavelId);
        processo.setResponsavel(responsavel);

        Servico[] servicos = getServicosByProcessoId(processo.getId());
        processo.setServicos(servicos);

        return processo;
    }

    private Servico[] getServicosByProcessoId(int processoId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT SERVICO_ID FROM T_PROCESSO_SERVICO WHERE PROCESSO_ID = ?";
        List<Servico> servicoList = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, processoId);
            try (ResultSet rs = stmt.executeQuery()) {
                ServicoDAO servicoDAO = new ServicoDAO();
                while (rs.next()) {
                    int servicoId = rs.getInt("SERVICO_ID");
                    Servico servico = servicoDAO.select(servicoId);
                    servicoList.add(servico);
                }
            }
        }
        return servicoList.toArray(new Servico[0]);
    }

    private void insertProcessoServicos(Connection conn, int processoId, Servico[] servicos) throws SQLException {
        String sql = "INSERT INTO T_PROCESSO_SERVICO (PROCESSO_ID, SERVICO_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Servico servico : servicos) {
                stmt.setInt(1, processoId);
                stmt.setInt(2, servico.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}
