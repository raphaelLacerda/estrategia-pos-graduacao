package br.estrategia.app.domain.service;

import br.estrategia.app.domain.exception.ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException;
import br.estrategia.app.domain.model.entidade.Disciplina;
import br.estrategia.app.domain.model.entidade.Professor;
import br.estrategia.app.domain.repository.ConcursoRepository;
import br.estrategia.app.domain.repository.ProfessorRepository;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ExtendWith(SerenityJUnit5Extension.class)
class CadastroDeDisciplinaBDDSerenityTest {


    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ConcursoRepository concursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;
    private Professor professor;

    @Steps
    private StepsCadastroDisciplina stepsCadastroDisciplina;

    @BeforeEach
    public void clean(){
        concursoRepository.deleteAll();
        disciplinaService.getDisciplinaRepository().deleteAll();
        professorRepository.deleteAll();
        professor = professorRepository.save(new Professor("rafa"));
    }

    @Test
    public void deve_salvar_disciplina_se_nao_tiver_professor_associado(){

        stepsCadastroDisciplina.preencherDisciplina("Engenharia de Requisitos", new BigDecimal("100"));
        stepsCadastroDisciplina.associarProfessor(null);
        stepsCadastroDisciplina.salvarDisciplina(disciplinaService);
    }
    @Test
    public void deve_salvar_disciplina_com_professor_associado(){

        stepsCadastroDisciplina.preencherDisciplina("Engenharia de Requisitos", new BigDecimal("100"));
        stepsCadastroDisciplina.associarProfessor(professor);
        stepsCadastroDisciplina.salvarDisciplina(disciplinaService);
    }

    @Test
    public void nao_deve_salvar_disciplina_se_professor_esta_associado_a_mais_de_duas_disciplinas(){

        stepsCadastroDisciplina.preencherDisciplina("Engenharia de Requisitos", new BigDecimal("100"));
        stepsCadastroDisciplina.professorJaEstejaCadastradoEmOutrasDisciplinas(disciplinaService, professor);
        stepsCadastroDisciplina.associarProfessor(professor);
        stepsCadastroDisciplina.naoDeveSalvarDisciplina(disciplinaService);
    }

}

@ContextConfiguration(classes = DisciplinaService.class)
class StepsCadastroDisciplina{

    Disciplina disciplina;

    @Step("Dado que disciplina foi preenchida com nome {0} e preco {1}")
    public void preencherDisciplina(String nome, BigDecimal preco){
        this.disciplina = new Disciplina(nome, preco);
    }
    @Step("Quando disciplina for associada a um professor {0}")
    public void associarProfessor(Professor professor){
        this.disciplina.setProfessor(professor);
    }
    @Step("Então disciplina deve ser cadastrada com sucesso")
    public void salvarDisciplina(DisciplinaService disciplinaService){
        this.disciplina = disciplinaService.save(this.disciplina);
        assertNotNull(this.disciplina.getId());
    }



    @Step("E Dado que professor {1} já esteja associado a mais de uma disciplina")
    public void professorJaEstejaCadastradoEmOutrasDisciplinas(DisciplinaService disciplinaService, Professor professor) {
        disciplinaService.save(new Disciplina("adm", new BigDecimal("10"), professor));
        disciplinaService.save(new Disciplina("const", new BigDecimal("10"), professor));
    }

    @Step("Então disciplina não pode ser cadastrada")
    public void naoDeveSalvarDisciplina(DisciplinaService disciplinaService){
        assertThrows(ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException.class,  ()->{
            disciplinaService.save(this.disciplina);
        });
    }
}
