package br.estrategia.app.domain.repository;

import br.estrategia.app.domain.model.entidade.Aluno;
import br.estrategia.app.domain.model.entidade.Concurso;
import br.estrategia.app.domain.model.entidade.Matricula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;


@Repository
public interface MatriculaRepository extends CrudRepository<Matricula, Long> {


    @Query("SELECT DISTINCT m FROM Matricula m inner join m.concurso c where c.dataDaProva < :data")
    Set<Matricula> listarMatriculasDeConcursosComDataDaProvaMenorQue(@Param("data") LocalDate data);

    Matricula findByAlunoAndConcurso(Aluno aluno, Concurso concurso);

    Set<Matricula> findByAluno(Aluno aluno);
}
