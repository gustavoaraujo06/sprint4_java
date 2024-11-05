package br.com.fiap.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.DAO.OficinaDAO;
import br.com.fiap.DAO.ServicoDAO;
import br.com.fiap.model.Servico;

public class ServicoService {

    public ResponseEntity<Servico> create(Servico servico) {
        try {
            if (servico.getDescricao() == null || servico.getDescricao().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getValor() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getDataCriacao() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getOficina() == null || servico.getOficina().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (servico.getValor() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getDuracao() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getDataCriacao().isAfter(LocalDate.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            OficinaDAO oficinaDAO = new OficinaDAO();
            if (!oficinaDAO.oficinaExiste(servico.getOficina().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            ServicoDAO servicoDAO = new ServicoDAO();
            servicoDAO.create(servico);
            return ResponseEntity.status(HttpStatus.CREATED).body(servico);

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

    public ResponseEntity<Servico> update(int id, Servico servico) {
        try {
            ServicoDAO servicoDAO = new ServicoDAO();
            if (!servicoDAO.servicoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (servico.getDescricao() == null || servico.getDescricao().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getValor() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getDataCriacao() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getOficina() == null || servico.getOficina().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (servico.getValor() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getDuracao() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (servico.getDataCriacao().isAfter(LocalDate.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            OficinaDAO oficinaDAO = new OficinaDAO();
            if (!oficinaDAO.oficinaExiste(servico.getOficina().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            servicoDAO.update(id, servico);
            return ResponseEntity.status(HttpStatus.OK).body(servico);

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

    public ResponseEntity<List<Servico>> select() {
        try {
            ServicoDAO servicoDAO = new ServicoDAO();
            return ResponseEntity.status(HttpStatus.OK).body(servicoDAO.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Servico> select(int id) {
        try {
            ServicoDAO servicoDAO = new ServicoDAO();
            if (!servicoDAO.servicoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(servicoDAO.select(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try {
            ServicoDAO servicoDAO = new ServicoDAO();
            if (!servicoDAO.servicoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serviço não existe!");
            }
            servicoDAO.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Serviço deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
        }
    }
}
