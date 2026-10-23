package br.com.sistema_monitoramento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relatorio_prioridade")
public class RelatorioPrioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qtUrgente;
    private int qtCritico;
    private int qtAtencao;
    private int qtNormal;
    private String resumo;
    public RelatorioPrioridade() {
    }

    public RelatorioPrioridade(
            int qtUrgente,
            int qtCritico,
            int qtAtencao,
            int qtNormal,
            String resumo) {

        this.qtUrgente = qtUrgente;
        this.qtCritico = qtCritico;
        this.qtAtencao = qtAtencao;
        this.qtNormal = qtNormal;
        this.resumo = resumo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtUrgente() {
        return qtUrgente;
    }

    public void setQtUrgente(int qtUrgente) {
        this.qtUrgente = qtUrgente;
    }

    public int getQtCritico() {
        return qtCritico;
    }

    public void setQtCritico(int qtCritico) {
        this.qtCritico = qtCritico;
    }

    public int getQtAtencao() {
        return qtAtencao;
    }

    public void setQtAtencao(int qtAtencao) {
        this.qtAtencao = qtAtencao;
    }

    public int getQtNormal() {
        return qtNormal;
    }

    public void setQtNormal(int qtNormal) {
        this.qtNormal = qtNormal;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    
}