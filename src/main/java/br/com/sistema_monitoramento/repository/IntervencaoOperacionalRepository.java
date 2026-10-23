package br.com.sistema_monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema_monitoramento.model.IntervencaoOperacional;

public interface IntervencaoOperacionalRepository
        extends JpaRepository<IntervencaoOperacional, Long> {

    List<IntervencaoOperacional> findByTipo(String tipo);

}