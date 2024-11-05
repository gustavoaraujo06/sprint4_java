package br.com.fiap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.model.Agente;
import br.com.fiap.service.AgenteService;

@RestController
@RequestMapping("/agentes")
public class AgenteController {

    private final AgenteService agenteService;

    public AgenteController() {
        this.agenteService = new AgenteService();
    }

    @PostMapping
    public ResponseEntity<Agente> create(@RequestBody Agente agente) {
        return this.agenteService.create(agente);
    }

    @GetMapping
    public ResponseEntity<List<Agente>> select() {
        return this.agenteService.select();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agente> select(@PathVariable int id) {
        return this.agenteService.select(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agente> update(@PathVariable int id, @RequestBody Agente agente) {
        return this.agenteService.update(id, agente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return this.agenteService.delete(id);
    }
}
