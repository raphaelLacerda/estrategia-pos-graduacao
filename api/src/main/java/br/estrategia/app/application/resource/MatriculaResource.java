
package br.estrategia.app.application.resource;

import br.estrategia.app.application.resource.suporte.AbstractRestBaseController;
import br.estrategia.app.domain.model.entidade.Aluno;
import br.estrategia.app.domain.model.entidade.Matricula;
import br.estrategia.app.domain.service.MatriculaService;
import br.estrategia.app.infra.rest.URI_API_PATHS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = URI_API_PATHS.MATRICULAS_API)
public class MatriculaResource extends AbstractRestBaseController<Matricula, Long> {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping(value = URI_API_PATHS.MATRICULAS_API + "/aluno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Matricula>> listarMatriculasDoAluno(@PathVariable("id") Long id) {


        return new ResponseEntity<>(matriculaService.getMatriculaRepository().findByAluno(new Aluno(id)),
                HttpStatus.OK);
    }

    @Override
    public CrudRepository<Matricula, Long> getRepositorio() {
        return matriculaService.getMatriculaRepository();
    }
}
