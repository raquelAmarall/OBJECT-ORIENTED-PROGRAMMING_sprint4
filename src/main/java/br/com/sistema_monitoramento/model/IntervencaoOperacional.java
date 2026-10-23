package br.com.sistema_monitoramento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "intervencao_operacional")
public class IntervencaoOperacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String descricao;
    
    @Column(name = "id_trecho")
    private Long idTrecho;

    public IntervencaoOperacional() {
    }

    public IntervencaoOperacional(String tipo, String descricao, Long idTrecho) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.idTrecho = idTrecho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdTrecho() {
        return idTrecho;
    }

    public void setIdTrecho(Long idTrecho) {
        this.idTrecho = idTrecho;
    }
}