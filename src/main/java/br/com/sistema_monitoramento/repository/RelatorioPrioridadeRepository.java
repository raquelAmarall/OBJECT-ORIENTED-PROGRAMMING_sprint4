package br.com.sistema_monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema_monitoramento.model.RelatorioPrioridade;

public interface RelatorioPrioridadeRepository
        extends JpaRepository<RelatorioPrioridade, Long> {

}