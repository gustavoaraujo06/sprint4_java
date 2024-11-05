package br.com.fiap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.model.Servico;
import br.com.fiap.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController() {
        this.servicoService = new ServicoService();
    }

    @PostMapping
    public ResponseEntity<Servico> create(@RequestBody Servico servico) {
        return this.servicoService.create(servico);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> select() {
        return this.servicoService.select();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> select(@PathVariable int id) {
        return this.servicoService.select(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> update(@PathVariable int id, @RequestBody Servico servico) {
        return this.servicoService.update(id, servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return this.servicoService.delete(id);
    }
}
