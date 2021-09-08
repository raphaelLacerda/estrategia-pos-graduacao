package br.estrategia.app.domain.model;

import br.estrategia.app.domain.model.entidade.Concurso;
import br.estrategia.app.domain.model.entidade.Disciplina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ConcursoTest {

    private Concurso concurso;

    @BeforeEach
    public void setUp(){
        concurso = new Concurso();
    }

    //Colocar um BUG na implementação > que ao invés de >= no adicionarDisciplina
    @Test
    public void deve_gerar_desconto_de_20_porcento_quando_houver_20_disciplinas(){
        for (int i = 0; i < 20; i++) {
            Disciplina disciplina = new Disciplina();
            disciplina.setPreco(new BigDecimal("10"));
            concurso.adicionarDisciplina(disciplina);
        }
        assertEquals(new BigDecimal("160.00"), concurso.getValor());
    }

    @Test
    public void deve_gerar_desconto_de_20_porcento_quando_houver_mais_de_20_disciplinas(){
        for (int i = 0; i < 30; i++) {
            Disciplina disciplina = new Disciplina();
            disciplina.setPreco(new BigDecimal("10.181"));
            concurso.adicionarDisciplina(disciplina);
        }
        assertEquals(new BigDecimal("244.32"), concurso.getValor());
    }

    @Test
    public void deve_gerar_desconto_de_10_porcento_quando_houver_mais_de_10_disciplinas(){
        for (int i = 0; i < 10; i++) {
            Disciplina disciplina = new Disciplina();
            disciplina.setPreco(new BigDecimal("10"));
            concurso.adicionarDisciplina(disciplina);
        }
        assertEquals(new BigDecimal("90.00"), concurso.getValor());
    }

    @Test
    public void deve_gerar_desconto_de_5_porcento_quando_houver_menos_de_10_disciplinas(){
        for (int i = 0; i < 9; i++) {
            Disciplina disciplina = new Disciplina();
            disciplina.setPreco(new BigDecimal("10"));
            concurso.adicionarDisciplina(disciplina);
        }
        assertEquals(new BigDecimal("85.50"), concurso.getValor());
    }

    @Test
    public void deve_gerar_desconto_de_50_porcento_para_concursos_em_pandemia(){

        Disciplina disciplina = new Disciplina();
        disciplina.setPreco(new BigDecimal("100"));
        concurso.adicionarDisciplina(disciplina);
        concurso.setDesconto(new DescontoPandemia());
        concurso.setDiaDoLancamento(LocalDate.of(2020, 10, 10));
        assertEquals(new BigDecimal("50.00"), concurso.getValor());
    }
    @Test
    public void nao_deve_gerar_desconto_de_50_porcento_para_concursos_diferente_de_2020(){

        concurso.setDesconto(new DescontoPandemia());

        concurso.setDiaDoLancamento(LocalDate.of(2021, 10, 10));
        Disciplina disciplina = new Disciplina();
        disciplina.setPreco(new BigDecimal("100"));
        concurso.adicionarDisciplina(disciplina);
//        assertEquals(new BigDecimal("100.00"), concurso.getValor());
        assertEquals(new BigDecimal("95.00"), concurso.getValor());

    }

    @Test
    public void deve_gerar_desconto_de_70_porcento_para_concursos_em_black_friday(){

        Disciplina disciplina = new Disciplina();
        disciplina.setPreco(new BigDecimal("100"));
        concurso.adicionarDisciplina(disciplina);
        concurso.setDesconto(new DescontoBlackFriday());
        concurso.setDiaDoLancamento(LocalDate.of(2020, 11, 10));
        assertEquals(new BigDecimal("30.00"), concurso.getValor());
    }

    //Não esquecer do setScale no setPreco para o ValorBruto ser igual ao Valor Líquido
    @Test
    public void caso_nao_seja_black_friday_deve_aplicar_desconto_de_disciplinas(){
        Disciplina disciplina = new Disciplina();
        disciplina.setPreco(new BigDecimal("100"));
        concurso.adicionarDisciplina(disciplina);
        concurso.setDesconto(new DescontoBlackFriday());
        concurso.setDiaDoLancamento(LocalDate.of(2020, 10, 10));
        assertEquals(new BigDecimal("95.00"), concurso.getValor());
    }

}
