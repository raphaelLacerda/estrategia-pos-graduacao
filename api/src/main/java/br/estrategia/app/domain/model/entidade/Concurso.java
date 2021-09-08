package br.estrategia.app.domain.model.entidade;

import br.estrategia.app.domain.model.Desconto;
import br.estrategia.app.domain.model.DescontoQuantidadeDisciplina;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Concurso {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String nome;
    private LocalDate diaDoLancamento = LocalDate.now();
    @NotNull
    private LocalDate dataDaProva;

    @Transient
    private Desconto desconto = new DescontoQuantidadeDisciplina();

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "id_concurso")}, inverseJoinColumns = {
            @JoinColumn(name = "id_disciplina")})
    private List<Disciplina> disciplinas = new ArrayList<>();


    public Concurso() {
    }

    public Concurso(@NotNull String nome, @NotNull LocalDate dataDaProva) {
        this.nome = nome;
        this.dataDaProva = dataDaProva;
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

//    public BigDecimal getValor() {
//        BigDecimal valorBruto = this.disciplinas.stream().map(Disciplina::getPreco)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        BigDecimal desconto;
//        if (this.disciplinas.size() >= 20) {
//            desconto = new BigDecimal("20");
//        } else if (this.disciplinas.size() >= 10) {
//            desconto = new BigDecimal("10");
//        } else {
//            desconto = new BigDecimal("5");
//        }
//
//        return valorBruto.subtract(valorBruto.multiply(desconto)
//                .divide(new BigDecimal(100)))
//                .setScale(2, RoundingMode.HALF_UP);
//    }

    public BigDecimal getValor() {
        BigDecimal valorBruto = this.getValorBruto();

        BigDecimal valorLiquido = valorBruto
                .subtract(desconto.aplicar(this))
                .setScale(2, RoundingMode.HALF_UP);

        if(valorBruto.equals(valorLiquido)){
            return valorBruto
                    .subtract(new DescontoQuantidadeDisciplina().aplicar(this))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return valorLiquido;
    }

    public BigDecimal getValorBruto() {
        return this.disciplinas.stream().map(Disciplina::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Concurso adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
        return this;
    }

    public LocalDate getDiaDoLancamento() {
        return diaDoLancamento;
    }

    public LocalDate getDataDaProva() {
        return dataDaProva;
    }

    public void setDataDaProva(LocalDate dataDaProva) {
        this.dataDaProva = dataDaProva;
    }

    public void setDiaDoLancamento(LocalDate diaDoLancamento) {
        this.diaDoLancamento = diaDoLancamento;
    }

    public void setDesconto(Desconto desconto) {
        this.desconto = desconto;
    }
}
