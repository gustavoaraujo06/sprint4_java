package br.com.fiap.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.DAO.AgenteDAO;
import br.com.fiap.DAO.DiagnosticoDAO;
import br.com.fiap.DAO.ProcessoDAO;
import br.com.fiap.DAO.ServicoDAO;
import br.com.fiap.DAO.VeiculoDAO;
import br.com.fiap.model.Processo;
import br.com.fiap.model.Servico;

public class ProcessoService {

    public ResponseEntity<Processo> create(Processo processo) {
        try {
            if (processo.getStatus() != 'A' && processo.getStatus() != 'F' && processo.getStatus() != 'C') {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getDescricao() == null || processo.getDescricao().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getVeiculo() == null || processo.getVeiculo().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getDiagnostico() == null || processo.getDiagnostico().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getResponsavel() == null || processo.getResponsavel().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (processo.getPrioridade() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getEstimativaConclusao() != null && processo.getEstimativaConclusao().isBefore(LocalDate.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            VeiculoDAO veiculoDAO = new VeiculoDAO();
            if (!veiculoDAO.veiculoExiste(processo.getVeiculo().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            if (!diagnosticoDAO.diagnosticoExiste(processo.getDiagnostico().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            AgenteDAO agenteDAO = new AgenteDAO();
            if (!agenteDAO.agenteExiste(processo.getResponsavel().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (processo.getServicos() != null) {
                ServicoDAO servicoDAO = new ServicoDAO();
                for (Servico servico : processo.getServicos()) {
                    if (!servicoDAO.servicoExiste(servico.getId())) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    }
                }
            }

            ProcessoDAO processoDAO = new ProcessoDAO();
            processoDAO.create(processo);
            return ResponseEntity.status(HttpStatus.CREATED).body(processo);

        } catch (SQLException e) {
            if (e.getErrorCode() == 1) { 
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Processo> update(int id, Processo processo) {
        try {
            ProcessoDAO processoDAO = new ProcessoDAO();
            if (!processoDAO.processoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (processo.getStatus() != 'A' && processo.getStatus() != 'F' && processo.getStatus() != 'C') {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getDescricao() == null || processo.getDescricao().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getVeiculo() == null || processo.getVeiculo().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getDiagnostico() == null || processo.getDiagnostico().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getResponsavel() == null || processo.getResponsavel().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (processo.getPrioridade() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (processo.getEstimativaConclusao() != null && processo.getEstimativaConclusao().isBefore(LocalDate.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            VeiculoDAO veiculoDAO = new VeiculoDAO();
            if (!veiculoDAO.veiculoExiste(processo.getVeiculo().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            if (!diagnosticoDAO.diagnosticoExiste(processo.getDiagnostico().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            AgenteDAO agenteDAO = new AgenteDAO();
            if (!agenteDAO.agenteExiste(processo.getResponsavel().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (processo.getServicos() != null) {
                ServicoDAO servicoDAO = new ServicoDAO();
                for (Servico servico : processo.getServicos()) {
                    if (!servicoDAO.servicoExiste(servico.getId())) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    }
                }
            }

            processoDAO.update(id, processo);
            return ResponseEntity.status(HttpStatus.OK).body(processo);

        } catch (SQLException e) {
            if (e.getErrorCode() == 1) { 
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<Processo>> select() {
        try {
            ProcessoDAO processoDAO = new ProcessoDAO();
            return ResponseEntity.status(HttpStatus.OK).body(processoDAO.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Processo> select(int id) {
        try {
            ProcessoDAO processoDAO = new ProcessoDAO();
            if (!processoDAO.processoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(processoDAO.select(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try {
            ProcessoDAO processoDAO = new ProcessoDAO();
            if (!processoDAO.processoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Processo não existe!");
            }
            processoDAO.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Processo deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
        }
    }
}
