package br.estrategia.app;

import br.estrategia.app.domain.model.DescontoBlackFriday;
import br.estrategia.app.domain.model.entidade.Concurso;
import br.estrategia.app.domain.model.entidade.Disciplina;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;


//@CucumberContextConfiguration
public class DescontoAplicadoEmConcursoBDDTest {

    private String today;
    private boolean actualAnswer;
    private Concurso concurso;

    @Given("dado que concurso com data {string} e preco {string}")
    public void dado_que_concurso_com_data_e_preco(String data, String preco) {
        Disciplina disciplina = new Disciplina();
        disciplina.setPreco(new BigDecimal(preco));
        this.concurso = new Concurso();
        this.concurso.adicionarDisciplina(disciplina);
        this.concurso.setDiaDoLancamento(LocalDate.parse(data));
    }

    @When("quando desconto de BlackFriday for aplicado")
    public void quando_desconto_de_black_friday_for_aplicado() {
        this.concurso.setDesconto(new DescontoBlackFriday());
    }

    @Then("entao o concurso deve ter seu valor de {string}")
    public void entao_o_concurso_deve_ter_seu_valor_de(String valor) {
        Assertions.assertEquals(new BigDecimal(valor), this.concurso.getValor());
    }
}



