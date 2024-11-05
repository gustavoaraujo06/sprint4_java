package br.com.fiap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.model.Diagnostico;
import br.com.fiap.service.DiagnosticoService;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    public DiagnosticoController() {
        this.diagnosticoService = new DiagnosticoService();
    }

    @PostMapping
    public ResponseEntity<Diagnostico> create(@RequestBody Diagnostico diagnostico) {
        return this.diagnosticoService.create(diagnostico);
    }

    @GetMapping
    public ResponseEntity<List<Diagnostico>> select() {
        return this.diagnosticoService.select();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> select(@PathVariable int id) {
        return this.diagnosticoService.select(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> update(@PathVariable int id, @RequestBody Diagnostico diagnostico) {
        return this.diagnosticoService.update(id, diagnostico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return this.diagnosticoService.delete(id);
    }
}
