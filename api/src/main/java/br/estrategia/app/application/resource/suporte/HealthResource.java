package br.estrategia.app.application.resource.suporte;

import br.estrategia.app.domain.repository.AlunoRepository;
import br.estrategia.app.infra.rest.URI_API_PATHS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthResource {

    @Autowired
    private AlunoRepository alunoRepository;

    @RequestMapping(value = URI_API_PATHS.READNIESS, method = RequestMethod.GET)
    public String isReady() {
        alunoRepository.findAll().size();
        return "{\"status\": \"ok\"}";
    }

    @RequestMapping(value = URI_API_PATHS.LIVENESS, method = RequestMethod.GET)
    public String isLive() {

        return "{\"status\": \"live\"}";
    }

    
}
