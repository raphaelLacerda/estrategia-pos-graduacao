package br.estrategia.app.domain.model.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "id_aluno", "id_concurso" }))
public class Matricula{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_concurso")
    private Concurso concurso;

    private BigDecimal avaliacao;

    private LocalDate dataEfetivacao = LocalDate.now();

    private boolean acessoPermitido = true;

    public Matricula() {
    }

    public Matricula(Aluno aluno, Concurso concurso) {
        this.aluno = aluno;
        this.concurso = concurso;
    }

    public Matricula inativarAcesso() {
        this.acessoPermitido = false;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public BigDecimal getAvaliacao() {
        return avaliacao;
    }

    public boolean isAcessoPermitido() {
        return acessoPermitido;
    }

    public LocalDate getDataEfetivacao() {
        return dataEfetivacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(id, matricula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
