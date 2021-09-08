package br.estrategia.app.domain.repository;

import br.estrategia.app.domain.model.entidade.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
