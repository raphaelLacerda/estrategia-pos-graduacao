package br.estrategia.app.domain.model.entidade;

import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String nome;

    @NotNull
    private BigDecimal preco;

    @ManyToOne
    private Professor professor;

    public Disciplina() {
    }

    public Disciplina(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Disciplina(String nome, BigDecimal preco, Professor professor) {
        this.nome = nome;
        this.preco = preco;
        this.professor = professor;
    }

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean possuiProfessor() {
        return this.professor!=null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
