package br.estrategia.app.domain.service;

import br.estrategia.app.domain.model.entidade.Aluno;
import br.estrategia.app.domain.model.entidade.Concurso;
import br.estrategia.app.domain.model.entidade.Matricula;
import br.estrategia.app.domain.repository.AlunoRepository;
import br.estrategia.app.domain.repository.ConcursoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MatriculaServiceTest {

    private Aluno aluno;
    @Autowired
    private AlunoRepository alunoRepository;

    private Concurso concursoPFComDataDaProvaFutura;
    @Autowired
    private ConcursoRepository concursoRepository;
    @Autowired
    private MatriculaService matriculaService;

    @BeforeEach
    public void setUp(){
        this.aluno = this.alunoRepository.save(new Aluno("rafa"));
        this.concursoPFComDataDaProvaFutura = this.concursoRepository.save(new Concurso("PF", LocalDate.of(2090,10,10)));
    }

    @AfterEach
    public void cleanUp(){
        this.matriculaService.getMatriculaRepository().deleteAll();
        this.alunoRepository.deleteAll();;
        this.concursoRepository.deleteAll();

    }

    @Test
    public void nao_deve_permitir_aluno_se_matricular_no_mesmo_concurso(){

        Matricula matricula = new Matricula(aluno, concursoPFComDataDaProvaFutura);
        matricula = matriculaService.getMatriculaRepository().save(matricula);
        assertNotNull(matricula.getId());
        assertTrue(matricula.isAcessoPermitido());

        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, ()->{
            Matricula matriculaNova = new Matricula(aluno, concursoPFComDataDaProvaFutura);
            matriculaService.getMatriculaRepository().save(matriculaNova);
        });
    }

    @Test
    public void deve_bloquear_acesso_para_concursos_que_ja_passaram_data_da_prova(){

        Concurso concursoPassouDataDaProva = this.concursoRepository.save(new Concurso("PRF", LocalDate.of(2010, 10, 10)));
        Matricula matricula = new Matricula(aluno, concursoPassouDataDaProva);
        matricula = matriculaService.getMatriculaRepository().save(matricula);
        assertNotNull(matricula.getId());
        assertTrue(matricula.isAcessoPermitido());

        Concurso concursoDataDaAtualProva = this.concursoRepository.save(new Concurso("PC", LocalDate.now()));
        matricula = new Matricula(aluno, concursoDataDaAtualProva);
        matricula = matriculaService.getMatriculaRepository().save(matricula);
        assertNotNull(matricula.getId());
        assertTrue(matricula.isAcessoPermitido());


        matriculaService.naoPermitirMaisAcessoDoAlunoAposDataDaProva();

        Matricula matriculaBloqueada = matriculaService.getMatriculaRepository().findByAlunoAndConcurso(aluno, concursoPassouDataDaProva);
        assertFalse(matriculaBloqueada.isAcessoPermitido());

    }
}
