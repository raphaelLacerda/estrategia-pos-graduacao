package br.estrategia.app.application.resource;

import br.estrategia.app.domain.model.DescontoBlackFriday;
import br.estrategia.app.domain.model.entidade.Concurso;
import br.estrategia.app.domain.model.entidade.Disciplina;
import br.estrategia.app.domain.repository.ConcursoRepository;
import br.estrategia.app.domain.repository.DisciplinaRepository;
import br.estrategia.app.infra.rest.URI_API_PATHS;
import br.estrategia.app.util.AbstractAPITesting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConcursoResourceTest extends AbstractAPITesting {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ConcursoRepository concursoRepository;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
        concursoRepository.deleteAll();
    }

    @Test
    public void deve_salvar_concurso() {

        Concurso concurso = new Concurso("PF", LocalDate.now());
        Disciplina admFinal = disciplinaRepository.save(new Disciplina("adm", new BigDecimal("100")));
        Disciplina constitucionalFinal = disciplinaRepository.save(new Disciplina("const", new BigDecimal("200")));

        concurso.setDiaDoLancamento(LocalDate.of(2021, 11, 10));


        web.post().uri(URI_API_PATHS.CONCURSOS_API)
                .accept(MediaType.ALL)
                .header("Authorization", TOKEN_ADMIN)
                .body(BodyInserters.fromValue(concurso))
                .exchange()
                .expectStatus().isCreated().expectBody(Concurso.class)
                .value(c -> {
                    assertTrue(c.getId() > 0);
                    c.setDesconto(new DescontoBlackFriday());
                    c.adicionarDisciplina(admFinal);
                    c.adicionarDisciplina(constitucionalFinal);

                    web.put().uri(URI_API_PATHS.CONCURSOS_API + "/" + c.getId())
                            .accept(MediaType.ALL)
                            .header("Authorization", TOKEN_ADMIN)
                            .body(BodyInserters.fromValue(c))
                            .exchange()
                            .expectStatus().isOk().expectBody(Concurso.class)
                            .value(concursoAtualizado -> {
                                concursoAtualizado.setDesconto(new DescontoBlackFriday());
                                assertEquals(2, concursoAtualizado.getDisciplinas().size());
                                assertEquals(new BigDecimal("90.00"), concursoAtualizado.getValor());
                            });
                });
    }
}
