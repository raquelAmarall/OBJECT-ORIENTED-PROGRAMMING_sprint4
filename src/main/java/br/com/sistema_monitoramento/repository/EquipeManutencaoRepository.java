package br.com.sistema_monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema_monitoramento.model.EquipeManutencao;

public interface EquipeManutencaoRepository
        extends JpaRepository<EquipeManutencao, Long> {

    List<EquipeManutencao> findByNome(String nome);

}