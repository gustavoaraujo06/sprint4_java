package br.com.fiap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.model.Oficina;
import br.com.fiap.service.OficinaService;

@RestController
@RequestMapping("/oficinas")
public class OficinaController {

    private final OficinaService oficinaService;

    public OficinaController() {
        this.oficinaService = new OficinaService();
    }

    @PostMapping
    public ResponseEntity<Oficina> create(@RequestBody Oficina oficina) {
        return this.oficinaService.create(oficina);
    }

    @GetMapping
    public ResponseEntity<List<Oficina>> select() {
        return this.oficinaService.select();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oficina> select(@PathVariable int id) {
        return this.oficinaService.select(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oficina> update(@PathVariable int id, @RequestBody Oficina oficina) {
        return this.oficinaService.update(id, oficina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return this.oficinaService.delete(id);
    }
}
