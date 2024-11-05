package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.DAO.DiagnosticoDAO;
import br.com.fiap.model.Diagnostico;

public class DiagnosticoService {

    public ResponseEntity<Diagnostico> create(Diagnostico diagnostico) {
        try {
            if (diagnostico.getDescricao() == null || diagnostico.getDescricao().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            diagnosticoDAO.create(diagnostico);
            return ResponseEntity.status(HttpStatus.CREATED).body(diagnostico);
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

    public ResponseEntity<Diagnostico> update(int id, Diagnostico diagnostico) {
        try {
            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            if (!diagnosticoDAO.diagnosticoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (diagnostico.getDescricao() == null || diagnostico.getDescricao().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            diagnosticoDAO.update(id, diagnostico);
            return ResponseEntity.status(HttpStatus.OK).body(diagnostico);
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

    public ResponseEntity<List<Diagnostico>> select() {
        try {
            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            return ResponseEntity.status(HttpStatus.OK).body(diagnosticoDAO.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Diagnostico> select(int id) {
        try {
            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            if (!diagnosticoDAO.diagnosticoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(diagnosticoDAO.select(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try {
            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            if (!diagnosticoDAO.diagnosticoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diagnóstico não existe!");
            }
            diagnosticoDAO.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Diagnóstico deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
        }
    }
}
