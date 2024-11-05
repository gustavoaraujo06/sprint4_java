package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.DAO.OficinaDAO;
import br.com.fiap.model.Oficina;

public class OficinaService {

    public ResponseEntity<Oficina> create(Oficina oficina) {
        try {
            if (oficina.getNome() == null || oficina.getNome().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getTelefone() == null || oficina.getTelefone().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getEmail() == null || oficina.getEmail().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getCnpj() == null || oficina.getCnpj().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (oficina.getCnpj().length() != 14) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getTelefone().length() > 15) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            OficinaDAO oficinaDAO = new OficinaDAO();
            oficinaDAO.create(oficina);
            return ResponseEntity.status(HttpStatus.CREATED).body(oficina);
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

    public ResponseEntity<Oficina> update(int id, Oficina oficina) {
        try {
            OficinaDAO oficinaDAO = new OficinaDAO();
            if (!oficinaDAO.oficinaExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (oficina.getNome() == null || oficina.getNome().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getTelefone() == null || oficina.getTelefone().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getEmail() == null || oficina.getEmail().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getCnpj() == null || oficina.getCnpj().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (oficina.getCnpj().length() != 14) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (oficina.getTelefone().length() > 15) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            oficinaDAO.update(id, oficina);
            return ResponseEntity.status(HttpStatus.OK).body(oficina);
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

    public ResponseEntity<List<Oficina>> select() {
        try {
            OficinaDAO oficinaDAO = new OficinaDAO();
            return ResponseEntity.status(HttpStatus.OK).body(oficinaDAO.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Oficina> select(int id) {
        try {
            OficinaDAO oficinaDAO = new OficinaDAO();
            if (!oficinaDAO.oficinaExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(oficinaDAO.select(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try {
            OficinaDAO oficinaDAO = new OficinaDAO();
            if (!oficinaDAO.oficinaExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oficina não existe!");
            }
            oficinaDAO.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Oficina deletada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
        }
    }
}
