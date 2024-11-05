package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.DAO.ClienteDAO;
import br.com.fiap.DAO.VeiculoDAO;
import br.com.fiap.model.Veiculo;

public class VeiculoService {

    public ResponseEntity<Veiculo> create(Veiculo veiculo) {
        try {
            if (veiculo.getChassi() == null || veiculo.getChassi().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getAno() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getMarca() == null || veiculo.getMarca().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getProprietario() == null || veiculo.getProprietario().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }


            ClienteDAO clienteDAO = new ClienteDAO();
            if (!clienteDAO.clienteExiste(veiculo.getProprietario().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.create(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
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

    public ResponseEntity<Veiculo> update(int id, Veiculo veiculo) {
        try {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            if (!veiculoDAO.veiculoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (veiculo.getChassi() == null || veiculo.getChassi().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getAno() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getMarca() == null || veiculo.getMarca().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (veiculo.getProprietario() == null || veiculo.getProprietario().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }


            ClienteDAO clienteDAO = new ClienteDAO();
            if (!clienteDAO.clienteExiste(veiculo.getProprietario().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            veiculoDAO.update(id, veiculo);
            return ResponseEntity.status(HttpStatus.OK).body(veiculo);
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

    public ResponseEntity<List<Veiculo>> select() {
        try {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            return ResponseEntity.status(HttpStatus.OK).body(veiculoDAO.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Veiculo> select(int id) {
        try {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            if (!veiculoDAO.veiculoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(veiculoDAO.select(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    public ResponseEntity<List<Veiculo>> selectByOwner(int proprietarioId) {
        try {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            return ResponseEntity.status(HttpStatus.OK).body(veiculoDAO.selectByOwner(proprietarioId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            if (!veiculoDAO.veiculoExiste(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não existe!");
            }
            veiculoDAO.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Veículo deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
        }
    }
}
