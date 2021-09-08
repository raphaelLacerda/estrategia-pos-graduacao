package br.estrategia.app.domain.model.entidade;

import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Professor {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String nome;
    private BigDecimal salario;

    public Professor() {
    }

    public Professor(String nome) {
        this.nome = nome;
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return id == professor.id &&
                Objects.equals(nome, professor.nome) &&
                Objects.equals(salario, professor.salario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, salario);
    }
}
