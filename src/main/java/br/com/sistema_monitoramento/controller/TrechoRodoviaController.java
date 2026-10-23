package br.com.sistema_monitoramento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sistema_monitoramento.model.TrechoRodovia;
import br.com.sistema_monitoramento.service.TrechoRodoviaService;

@RestController
@RequestMapping("/api/trechos")
public class TrechoRodoviaController {

    private final TrechoRodoviaService service;

    public TrechoRodoviaController(TrechoRodoviaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TrechoRodovia>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrechoRodovia> buscarPorId(@PathVariable Long id) {

        TrechoRodovia trecho = service.buscarPorId(id);

        if (trecho == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trecho);
    }

    @PostMapping
    public ResponseEntity<TrechoRodovia> criar(
            @RequestBody TrechoRodovia trecho) {

        TrechoRodovia novoTrecho = service.criar(trecho);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoTrecho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrechoRodovia> atualizar(
            @PathVariable Long id,
            @RequestBody TrechoRodovia trecho) {

        TrechoRodovia trechoAtualizado =
                service.atualizar(id, trecho);

        if (trechoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trechoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        boolean removido = service.deletar(id);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}