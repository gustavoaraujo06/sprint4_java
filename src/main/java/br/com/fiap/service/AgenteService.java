package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.DAO.AgenteDAO;
import br.com.fiap.model.Agente;

public class AgenteService {

    public ResponseEntity<Agente> create(Agente agente) {
        try {
            if (agente.getNome() == null || agente.getNome().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getCpf() == null || agente.getCpf().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getRg() == null || agente.getRg().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getEmail() == null || agente.getEmail().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (agente.getCpf().length() != 11) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getRg().length() > 15) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            AgenteDAO agenteDAO = new AgenteDAO();
            agenteDAO.create(agente);
            return ResponseEntity.status(HttpStatus.CREATED).body(agente);
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

    public ResponseEntity<Agente> update(int id, Agente agente) {
        try {
            AgenteDAO agenteDAO = new AgenteDAO();
            if (!agenteDAO.agenteExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (agente.getNome() == null || agente.getNome().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getCpf() == null || agente.getCpf().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getRg() == null || agente.getRg().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getEmail() == null || agente.getEmail().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (agente.getCpf().length() != 11) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (agente.getRg().length() > 15) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            agenteDAO.update(id, agente);
            return ResponseEntity.status(HttpStatus.OK).body(agente);
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

    public ResponseEntity<List<Agente>> select() {
        try {
            AgenteDAO agenteDAO = new AgenteDAO();
            return ResponseEntity.status(HttpStatus.OK).body(agenteDAO.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Agente> select(int id) {
        try {
            AgenteDAO agenteDAO = new AgenteDAO();
            if (!agenteDAO.agenteExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(agenteDAO.select(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try {
            AgenteDAO agenteDAO = new AgenteDAO();
            if (!agenteDAO.agenteExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente não existe!");
            }
            agenteDAO.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Agente deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
        }
    }
}
