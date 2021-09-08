package br.estrategia.app.domain.repository;

import br.estrategia.app.domain.model.entidade.Concurso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;


@Repository
public interface ConcursoRepository extends CrudRepository<Concurso, Long> {

    @Query("SELECT DISTINCT c FROM Concurso c where c.dataDaProva > :data")
    Set<Concurso> listarConcursosDisponiveis(@Param("data") LocalDate data);

}
