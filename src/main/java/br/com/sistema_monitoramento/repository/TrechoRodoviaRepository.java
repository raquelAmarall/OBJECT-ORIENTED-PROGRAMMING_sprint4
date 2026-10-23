package br.com.sistema_monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema_monitoramento.model.TrechoRodovia;

public interface TrechoRodoviaRepository
        extends JpaRepository<TrechoRodovia, Long> {

    List<TrechoRodovia> findByNivelVegetacaoGreaterThanEqual(double minimo);

}