package br.com.sistema_monitoramento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistema_monitoramento.model.TrechoRodovia;
import br.com.sistema_monitoramento.repository.TrechoRodoviaRepository;

@Service
public class TrechoRodoviaService {

    private final TrechoRodoviaRepository repository;

    public TrechoRodoviaService(TrechoRodoviaRepository repository) {
        this.repository = repository;
    }

    public List<TrechoRodovia> listarTodos() {
        return repository.findAll();
    }

    public TrechoRodovia buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TrechoRodovia criar(TrechoRodovia trecho) {
        validarTrecho(trecho);
        return repository.save(trecho);
    }

    public TrechoRodovia atualizar(Long id, TrechoRodovia trecho){

        TrechoRodovia existente = repository.findById(id).orElse(null);

        if (existente == null) {
            return null;
        }

        validarTrecho(trecho);

        existente.setQuilometroInicial(trecho.getQuilometroInicial());
        existente.setQuilometroFinal(trecho.getQuilometroFinal());
        existente.setNivelVegetacao(trecho.getNivelVegetacao());
        existente.setNome(trecho.getNome());
        existente.setUmido(trecho.getUmido());
        existente.setMonitorado(trecho.getMonitorado());

        return repository.save(existente);
    }

    public boolean deletar(Long id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public List<TrechoRodovia> buscarPorNivelVegetacao(double minimo) {
        return repository.findByNivelVegetacaoGreaterThanEqual(minimo);
    }

    private void validarTrecho(TrechoRodovia trecho) {

        if (trecho.getQuilometroFinal() < trecho.getQuilometroInicial()) {
            throw new IllegalArgumentException(
                "Quilômetro final não pode ser menor que o quilômetro inicial."
            );
        }

        if (trecho.getNivelVegetacao() < 0) {
            throw new IllegalArgumentException(
                "Nível de vegetação não pode ser negativo."
            );
        }

        if (trecho.getNome() == null || trecho.getNome().isBlank()) {
            throw new IllegalArgumentException(
                "Nome do trecho é obrigatório."
            );
        }
    }
}