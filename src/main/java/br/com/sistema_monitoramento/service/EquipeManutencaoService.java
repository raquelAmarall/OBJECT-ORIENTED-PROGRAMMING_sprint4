package br.com.sistema_monitoramento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistema_monitoramento.model.EquipeManutencao;
import br.com.sistema_monitoramento.repository.EquipeManutencaoRepository;

@Service
public class EquipeManutencaoService {

    private final EquipeManutencaoRepository repository;

    public EquipeManutencaoService(EquipeManutencaoRepository repository) {
        this.repository = repository;
    }

    public List<EquipeManutencao> listarTodos() {
        return repository.findAll();
    }

    public EquipeManutencao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public EquipeManutencao salvar(EquipeManutencao equipe) {
        return repository.save(equipe);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}