package br.estrategia.app.application.resource.suporte;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@RestController
@Transactional
public abstract class AbstractRestBaseController<T, K> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestBaseController.class);

    public abstract CrudRepository<T, K> getRepositorio();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> find(@PathVariable("id") K id) {

        Optional<T> objeto = getRepositorio().findById(id);
        LOG.debug("Objeto pesquisado {}", objeto);

        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<T>> list() {

        return new ResponseEntity(getRepositorio().findAll(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> save(@RequestBody T t) {

        return new ResponseEntity(getRepositorio().save(t), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@PathVariable("id") K id, @RequestBody T t) {

        Optional<T> objeto = getRepositorio().findById(id);
        if (objeto.isPresent()) {
            getRepositorio().save(t);
            return new ResponseEntity(t, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> delete(@PathVariable("id") K id) {

        getRepositorio().deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
