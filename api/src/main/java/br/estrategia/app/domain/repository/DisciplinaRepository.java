package br.estrategia.app.domain.repository;

import br.estrategia.app.domain.model.entidade.Disciplina;
import br.estrategia.app.domain.model.entidade.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

    Set<Disciplina> findByProfessor(Professor professor);
}
