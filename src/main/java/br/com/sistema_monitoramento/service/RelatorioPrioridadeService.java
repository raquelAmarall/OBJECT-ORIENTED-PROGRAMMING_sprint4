package br.com.sistema_monitoramento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistema_monitoramento.model.RelatorioPrioridade;
import br.com.sistema_monitoramento.model.TrechoRodovia;
import br.com.sistema_monitoramento.repository.RelatorioPrioridadeRepository;
import br.com.sistema_monitoramento.repository.TrechoRodoviaRepository;

@Service
public class RelatorioPrioridadeService {

    private final RelatorioPrioridadeRepository repository;
    private final TrechoRodoviaRepository trechoRepository;

    public RelatorioPrioridadeService(
            RelatorioPrioridadeRepository repository,
            TrechoRodoviaRepository trechoRepository) {

        this.repository = repository;
        this.trechoRepository = trechoRepository;
    }

    public RelatorioPrioridade gerarRelatorio() {

        List<TrechoRodovia> trechos = trechoRepository.findAll();

        int urgente = 0;
        int critico = 0;
        int atencao = 0;
        int normal = 0;

        for (TrechoRodovia trecho : trechos) {

            double nivel = trecho.getNivelVegetacao();

            if (nivel >= 80) {
                urgente++;
            } else if (nivel >= 50) {
                critico++;
            } else if (nivel >= 25) {
                atencao++;
            } else {
                normal++;
            }
        }

        String resumo =
                "Relatório gerado com " + trechos.size() + " trecho(s).";

        RelatorioPrioridade relatorio =
                new RelatorioPrioridade(
                        urgente,
                        critico,
                        atencao,
                        normal,
                        resumo
                );

        return repository.save(relatorio);
    }

    public List<RelatorioPrioridade> listarHistorico() {
        return repository.findAll();
    }
}