package br.com.fiap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Cliente;
import br.com.fiap.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private final ClienteService clienteService;
	public ClienteController() {
		this.clienteService = new ClienteService();
	}
	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
		return this.clienteService.create(cliente);
	}
	@GetMapping
	public ResponseEntity<List<Cliente>> select(){
		return this.clienteService.select();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> select(@PathVariable int id){
		return this.clienteService.select(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable int id, @RequestBody Cliente cliente){
		return this.clienteService.update(id, cliente);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id){
		return this.clienteService.delete(id);
	}
}
