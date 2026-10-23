package br.com.sistema_monitoramento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sistema_monitoramento.model.RelatorioPrioridade;
import br.com.sistema_monitoramento.service.RelatorioPrioridadeService;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioPrioridadeController {

    private final RelatorioPrioridadeService service;

    public RelatorioPrioridadeController(
            RelatorioPrioridadeService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RelatorioPrioridade> gerarRelatorio() {

        RelatorioPrioridade relatorio =
                service.gerarRelatorio();

        return ResponseEntity.status(201).body(relatorio);
    }

    @GetMapping
    public ResponseEntity<List<RelatorioPrioridade>> listarHistorico() {

        return ResponseEntity.ok(
                service.listarHistorico()
        );
    }
}