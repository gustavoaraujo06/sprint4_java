package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.DAO.ClienteDAO;
import br.com.fiap.model.Cliente;

public class ClienteService {
	public ResponseEntity<Cliente> create(Cliente cliente) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.create(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		}catch(SQLException e) {
			if(e.getErrorCode() == 1) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	public ResponseEntity<Cliente> update(int id, Cliente cliente) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			if(!clienteDAO.clienteExiste(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			clienteDAO.update(id, cliente);
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		}catch(SQLException e) {
			if(e.getErrorCode() == 1) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	public ResponseEntity<List<Cliente>> select() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			return ResponseEntity.status(HttpStatus.OK).body(clienteDAO.select());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	public ResponseEntity<Cliente> select(int id) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			if(!clienteDAO.clienteExiste(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(clienteDAO.select(id));
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	public ResponseEntity<String> delete(int id) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			if(!clienteDAO.clienteExiste(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao existe!");
			}
			clienteDAO.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
		}catch(SQLException e) {
			if(e.getErrorCode() == 2292) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente ainda tem associações!");
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno :(");
		}
	}
}
