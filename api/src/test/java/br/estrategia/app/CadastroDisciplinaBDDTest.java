package br.estrategia.app;

import br.estrategia.app.domain.exception.ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException;
import br.estrategia.app.domain.model.entidade.Disciplina;
import br.estrategia.app.domain.model.entidade.Professor;
import br.estrategia.app.domain.repository.ProfessorRepository;
import br.estrategia.app.domain.service.DisciplinaService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@CucumberContextConfiguration
public class CadastroDisciplinaBDDTest {

    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private ProfessorRepository professorRepository;
    Disciplina disciplina;

    @Given("dado que disciplina foi preenchida {string}")
    public void dado_que_existe_disciplina_foi_preenchida(String nome) {
        disciplinaService.getDisciplinaRepository().deleteAll();
        professorRepository.deleteAll();

        this.disciplina = new Disciplina(nome);
    }

    @When("quando for associado professor que tem {string} disciplinas")
    public void quando_for_associado_professor(String disciplinas) {
        int totalDisciplinas = Integer.parseInt(disciplinas);
        if (totalDisciplinas == 0) {
            this.disciplina.setProfessor(null);
            return;
        }
        Professor professor = professorRepository.save(new Professor("rafa"));
        for (int i = 0; i < totalDisciplinas; i++) {
            Disciplina disciplinaDoProfessor = new Disciplina("teste" + i);
            disciplinaDoProfessor.setProfessor(professor);
            disciplinaService.save(disciplinaDoProfessor);
        }
        this.disciplina.setProfessor(professor);
    }

    @Then("entao disciplina deve ser cadastrada com sucesso")
    public void entao_disciplina_deve_ser_cadastrada_com_sucesso() {
        this.disciplina = disciplinaService.save(this.disciplina);
        assertNotNull(this.disciplina.getId());
    }

    @Then("entao disciplina nao deve ser cadastrada")
    public void entao_disciplina_nao_deve_ser_cadastrada_com_sucesso() {
        assertThrows(ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException.class,  ()->{
            disciplinaService.save(this.disciplina);
        });
    }


}