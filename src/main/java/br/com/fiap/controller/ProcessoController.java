package br.com.fiap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.model.Processo;
import br.com.fiap.service.ProcessoService;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    private final ProcessoService processoService;

    public ProcessoController() {
        this.processoService = new ProcessoService();
    }

    @PostMapping
    public ResponseEntity<Processo> create(@RequestBody Processo processo) {
        return this.processoService.create(processo);
    }

    @GetMapping
    public ResponseEntity<List<Processo>> select() {
        return this.processoService.select();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processo> select(@PathVariable int id) {
        return this.processoService.select(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Processo> update(@PathVariable int id, @RequestBody Processo processo) {
        return this.processoService.update(id, processo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return this.processoService.delete(id);
    }
}
