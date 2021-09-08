package br.estrategia.app.domain.service;

import br.estrategia.app.domain.excecao.ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException;
import br.estrategia.app.domain.model.entidade.Disciplina;
import br.estrategia.app.domain.model.entidade.Professor;
import br.estrategia.app.domain.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class DisciplinaServiceTest {


    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorRepository professorRepository;
    private Professor professor;

    @BeforeEach
    public void clean(){
        disciplinaService.getDisciplinaRepository().deleteAll();
        professorRepository.deleteAll();

        professor = professorRepository.save(new Professor("rafa"));
    }

    @Test
    public void deve_salvar_disciplina_se_nao_tiver_professor_associado(){

        Disciplina adm = disciplinaService.save(new Disciplina("adm", new BigDecimal("10")));
        assertNotNull(adm.getId());
    }

    @Test
    public void deve_salvar_disciplina_com_professor_que_esta_associado_a_ate_duas_disciplinas(){

        Disciplina adm = disciplinaService.save(new Disciplina("adm", new BigDecimal("10"), professor));
        assertNotNull(adm.getId());
        assertEquals(professor, adm.getProfessor());

        Disciplina consti = disciplinaService.save(new Disciplina("const", new BigDecimal("10"), professor));
        assertNotNull(consti.getId());
        assertEquals(professor, consti.getProfessor());

    }

    @Test
    public void nao_deve_salvar_disciplina_se_professor_esta_associado_a_mais_de_duas_disciplinas(){
        deve_salvar_disciplina_com_professor_que_esta_associado_a_ate_duas_disciplinas();
        assertThrows(ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException.class,  ()->{
            System.out.println("inicio");
            disciplinaService.save(new Disciplina("terceira", new BigDecimal("10"), professor));
        });
        assertEquals(2, disciplinaService.getDisciplinaRepository().findByProfessor(professor).size());
    }

}
