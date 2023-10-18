package br.estrategia.app.domain.model;

import br.estrategia.app.domain.model.entidade.Concurso;
import br.estrategia.app.domain.model.entidade.Disciplina;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.time.LocalDate;


@ExtendWith(SerenityJUnit5Extension.class)
class CalculoDeDescontoAplicadoNoConcursoBDDSerenityTest {

    private Concurso concurso;

    @Steps
    StepsCadastroDeConcurso stepsCadastroDeConcurso;


    @BeforeEach
    public void setUp(){
        concurso = new Concurso();
    }

    @Test
    public void deve_gerar_desconto_de_70_porcento_para_concursos_em_epoca_de_black_friday(){
        //GIVEN
        stepsCadastroDeConcurso.
                concursoCadastradoNumaData(
                        LocalDate.of(2023, 11, 10),
                        new BigDecimal("100"));

        //WHEN
        stepsCadastroDeConcurso.descontoForAplicado(new DescontoBlackFriday());

        //THEN
        stepsCadastroDeConcurso.deveTerDescontoCalculado(new BigDecimal("30.00"));
    }

    @Test
    public void nao_deve_gerar_desconto_caso_nao_seja_black_friday_nesse_caso_deve_apenas_aplicar_desconto_de_disciplinas_de_5_porcento(){
        //GIVEN
        stepsCadastroDeConcurso.
                concursoCadastradoNumaData(
                        LocalDate.of(2023, 10, 10),
                        new BigDecimal("100"));

        //WHEN
        stepsCadastroDeConcurso.descontoForAplicado(new DescontoBlackFriday());

        //THEN
        stepsCadastroDeConcurso.deveTerDescontoCalculado(new BigDecimal("95.00"));
    }
    @Test
    public void deve_gerar_desconto_de_50_porcento_para_concursos_em_epoca_de_pandemia(){
        //GIVEN
        stepsCadastroDeConcurso.
                concursoCadastradoNumaData(
                        LocalDate.of(2020, 10, 10),
                        new BigDecimal("100"));

        //WHEN
        stepsCadastroDeConcurso.descontoForAplicado(new DescontoPandemia());

        //THEN
        stepsCadastroDeConcurso.deveTerDescontoCalculado(new BigDecimal("50.00"));
    }

    @Test
    public void deve_gerar_desconto_de_20_porcento_quando_houver_20_disciplinas(){
        //GIVEN
        stepsCadastroDeConcurso.
                concursoComMaisDeUmaDisciplina(20, new BigDecimal("10"));

        //WHEN
        stepsCadastroDeConcurso.descontoForAplicado(new DescontoQuantidadeDisciplina());

        //THEN
        stepsCadastroDeConcurso.deveTerDescontoCalculado(new BigDecimal("160.00"));
    }

    @Test
    public void deve_gerar_desconto_de_20_porcento_quando_houver_mais_de_20_disciplinas(){
        //GIVEN
        stepsCadastroDeConcurso.
                concursoComMaisDeUmaDisciplina(30, new BigDecimal("10"));

        //WHEN
        stepsCadastroDeConcurso.descontoForAplicado(new DescontoQuantidadeDisciplina());

        //THEN
        stepsCadastroDeConcurso.deveTerDescontoCalculado(new BigDecimal("240.00"));
    }
    @Test
    public void deve_gerar_desconto_de_5_porcento_quando_houver_ate_10_disciplinas(){
        //GIVEN
        stepsCadastroDeConcurso.
                concursoComMaisDeUmaDisciplina(8, new BigDecimal("10"));

        //WHEN
        stepsCadastroDeConcurso.descontoForAplicado(new DescontoQuantidadeDisciplina());

        //THEN
        stepsCadastroDeConcurso.deveTerDescontoCalculado(new BigDecimal("76.00"));
    }

}

class StepsCadastroDeConcurso{

    private Concurso concurso;

    @Step("Dado que concurso foi cadastrado com data {0} e disciplina com preço {1}")
    public Concurso concursoCadastradoNumaData(LocalDate data, BigDecimal preco) {
        Disciplina disciplina = new Disciplina();
        disciplina.setPreco(preco);
        this.concurso = new Concurso();
        this.concurso.adicionarDisciplina(disciplina);
        this.concurso.setDiaDoLancamento(data);

        return this.concurso;
    }
    @Step("Dado que concurso foi cadastrado com {0} disciplinas")
    public Concurso concursoComMaisDeUmaDisciplina(int totalDeDisicplinas, BigDecimal preco) {
        this.concurso = new Concurso();
        for (int i = 0; i < totalDeDisicplinas; i++) {
            Disciplina disciplina = new Disciplina();
            disciplina.setPreco(preco);
            concurso.adicionarDisciplina(disciplina);
        }
        return this.concurso;
    }

    @Step("Quando desconto {0} for aplicado")
    public void descontoForAplicado(Desconto desconto) {
        this.concurso.setDesconto(desconto);
    }


    @Step("Então o valor do preço do concurso deve ser {0}")
    public void deveTerDescontoCalculado(BigDecimal preco) {
        Assertions.assertEquals(this.concurso.getValor(), preco);
    }
}
