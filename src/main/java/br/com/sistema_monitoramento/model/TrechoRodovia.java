package br.com.sistema_monitoramento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trecho_rodovia")
public class TrechoRodovia implements MonitoravelViaIoT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quilometroInicial;
    private double quilometroFinal;
    private double nivelVegetacao;
    private String nome;
    private boolean umido;
    private boolean monitorado;

    // Construtor vazio exigido pelo JPA
    public TrechoRodovia() {
    }

    // Construtor
    public TrechoRodovia(double quilometroInicial, double quilometroFinal,
            double nivelVegetacao, String nome, boolean umido, boolean monitorado) {

        this.setNivelVegetacao(nivelVegetacao);
        this.setQuilometroInicial(quilometroInicial);
        this.setQuilometroFinal(quilometroFinal);
        this.setNome(nome);
        this.umido = umido;
        this.monitorado = monitorado;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuilometroInicial() {
        return quilometroInicial;
    }

    public void setQuilometroInicial(double quilometroInicial) {
        this.quilometroInicial = quilometroInicial;
    }

    public double getQuilometroFinal() {
        return quilometroFinal;
    }

    public void setQuilometroFinal(double quilometroFinal) {
        if (quilometroFinal < this.getQuilometroInicial()) {
            System.out.println(
                "Quilômetro final está menor que o quilômetro inicial. " +
                "Valor ajustado para ser o mesmo informado no Quilômetro Inicial;"
            );

            this.quilometroFinal = this.getQuilometroInicial();

        } else {
            this.quilometroFinal = quilometroFinal;
        }
    }

    public double getNivelVegetacao() {
        return nivelVegetacao;
    }

    public void setNivelVegetacao(double nivelVegetacao) {
        if (nivelVegetacao < 0) {
            System.out.println(
                "O nivel de vegetação não pode ser negativo. Valor definido como 0."
            );

            this.nivelVegetacao = 0;

        } else {
            this.nivelVegetacao = nivelVegetacao;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null) {
            System.out.println("Nome não informado.");
            this.nome = "Nome não informado";

        } else {
            this.nome = nome;
        }
    }

    public boolean getUmido() {
        return umido;
    }

    public boolean getMonitorado() {
        return monitorado;
    }
    public void setUmido(boolean umido) {
        this.umido = umido;
    }

    public void setMonitorado(boolean monitorado) {
        this.monitorado = monitorado;
    }

    // Métodos

    public void registrarCrescimento(double taxa) {

        double crescimento = this.getNivelVegetacao();

        if (taxa > 0) {

            if (this.umido) {
                crescimento += taxa * 2;
            } else {
                crescimento += taxa;
            }

        } else {
            System.out.println(
                "A taxa de crescimento tem que ser maior que 0"
            );
        }

        this.setNivelVegetacao(crescimento);
    }

    @Override
    public String transmitirDadosSensor() {

        String mensagem =
            "No trecho: " + this.getNome() +
            " o nível da vegetação é de: " +
            this.getNivelVegetacao();

        if (this.monitorado) {
            return mensagem;

        } else {
            return "Trecho " + this.getNome() +
                   " não tem tecnologia de monitoramento";
        }
    }
}