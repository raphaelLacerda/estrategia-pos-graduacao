package br.estrategia.app.domain.repository;

import br.estrategia.app.domain.model.entidade.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

    List<Aluno> findAll();
}
