package br.com.sistema_monitoramento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistema_monitoramento.model.IntervencaoOperacional;
import br.com.sistema_monitoramento.repository.IntervencaoOperacionalRepository;

@Service
public class IntervencaoOperacionalService {

    private final IntervencaoOperacionalRepository repository;

    public IntervencaoOperacionalService(IntervencaoOperacionalRepository repository) {
        this.repository = repository;
    }

    public List<IntervencaoOperacional> listarTodos() {
        return repository.findAll();
    }

    public IntervencaoOperacional buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public IntervencaoOperacional salvar(IntervencaoOperacional intervencao) {
        return repository.save(intervencao);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}