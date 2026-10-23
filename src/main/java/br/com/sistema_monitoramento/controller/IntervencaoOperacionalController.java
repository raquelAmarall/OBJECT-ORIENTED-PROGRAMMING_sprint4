package br.com.sistema_monitoramento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sistema_monitoramento.model.IntervencaoOperacional;
import br.com.sistema_monitoramento.service.IntervencaoOperacionalService;

@RestController
@RequestMapping("/api/intervencoes")
public class IntervencaoOperacionalController {

    private final IntervencaoOperacionalService service;

    public IntervencaoOperacionalController(
            IntervencaoOperacionalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<IntervencaoOperacional>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntervencaoOperacional> buscarPorId(
            @PathVariable Long id) {

        IntervencaoOperacional intervencao = service.buscarPorId(id);

        if (intervencao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(intervencao);
    }

    @PostMapping
    public ResponseEntity<IntervencaoOperacional> criar(
            @RequestBody IntervencaoOperacional intervencao) {

        IntervencaoOperacional novaIntervencao =
                service.salvar(intervencao);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novaIntervencao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntervencaoOperacional> atualizar(
            @PathVariable Long id,
            @RequestBody IntervencaoOperacional intervencao) {

        IntervencaoOperacional existente =
                service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setTipo(intervencao.getTipo());
        existente.setDescricao(intervencao.getDescricao());
        existente.setIdTrecho(intervencao.getIdTrecho());

        return ResponseEntity.ok(service.salvar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        IntervencaoOperacional existente =
                service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}