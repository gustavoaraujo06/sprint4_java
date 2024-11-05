package br.com.fiap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.model.Veiculo;
import br.com.fiap.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController() {
        this.veiculoService = new VeiculoService();
    }

    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody Veiculo veiculo) {
        return this.veiculoService.create(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> select(@RequestParam(required = false) Integer proprietarioId ) {
    	if(proprietarioId != null) {
    		return this.veiculoService.selectByOwner(proprietarioId);
    	}
        return this.veiculoService.select();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> selectById(@PathVariable int id) {
        return this.veiculoService.select(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable int id, @RequestBody Veiculo veiculo) {
        return this.veiculoService.update(id, veiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return this.veiculoService.delete(id);
    }
}
