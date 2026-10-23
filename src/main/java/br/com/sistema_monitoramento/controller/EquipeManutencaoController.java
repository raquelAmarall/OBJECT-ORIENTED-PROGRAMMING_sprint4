package br.com.sistema_monitoramento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sistema_monitoramento.model.EquipeManutencao;
import br.com.sistema_monitoramento.service.EquipeManutencaoService;

@RestController
@RequestMapping("/api/equipes")
public class EquipeManutencaoController {

    private final EquipeManutencaoService service;

    public EquipeManutencaoController(EquipeManutencaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EquipeManutencao>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeManutencao> buscarPorId(@PathVariable Long id) {

        EquipeManutencao equipe = service.buscarPorId(id);

        if (equipe == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(equipe);
    }

    @PostMapping
    public ResponseEntity<EquipeManutencao> criar(
            @RequestBody EquipeManutencao equipe) {

        EquipeManutencao novaEquipe = service.salvar(equipe);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novaEquipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeManutencao> atualizar(
            @PathVariable Long id,
            @RequestBody EquipeManutencao equipe) {

        EquipeManutencao existente = service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNome(equipe.getNome());

        return ResponseEntity.ok(service.salvar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        EquipeManutencao existente = service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}