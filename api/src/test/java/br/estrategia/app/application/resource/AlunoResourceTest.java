package br.estrategia.app.application.resource;

import br.estrategia.app.domain.model.entidade.Aluno;
import br.estrategia.app.domain.repository.AlunoRepository;
import br.estrategia.app.infra.rest.URI_API_PATHS;
import br.estrategia.app.util.AbstractAPITesting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoResourceTest extends AbstractAPITesting {

    @Autowired
    private AlunoRepository alunoRepository;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
        alunoRepository.deleteAll();
    }

    @Test
    public void deve_salvar_aluno() {

        Aluno aluno = new Aluno("rafa");

        web.post().uri(URI_API_PATHS.ALUNOS_API)
                .header("Authorization", TOKEN_ADMIN)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectStatus().isCreated().expectBody(Aluno.class)
                .value(c -> assertTrue(c.getId() > 0));
    }

    @Test
    public void deve_listar_alunos() {

        deve_salvar_aluno();
        deve_salvar_aluno();
        deve_salvar_aluno();

        conferirAlunosListados(3);
    }

    @Test
    public void deve_atualizar_alunos() {
        Aluno aluno = new Aluno();
        aluno.setNome("raphael lacerda");

        web.post().uri(URI_API_PATHS.ALUNOS_API)
                .accept(MediaType.ALL)
                .header("Authorization", TOKEN_ADMIN)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectStatus().isCreated().expectBody(Aluno.class)
                .value(c -> {
                    assertEquals("raphael lacerda", c.getNome());
                    aluno.setId(c.getId());
                    aluno.setNome("raphael lacerda atualizado");

                    web.put().uri(URI_API_PATHS.ALUNOS_API + "/" + c.getId())
                            .accept(MediaType.ALL)
                            .header("Authorization", TOKEN_ADMIN)
                            .body(BodyInserters.fromValue(aluno))
                            .exchange()
                            .expectStatus().isOk().expectBody(Aluno.class)
                            .value(alunoAtualizado -> assertEquals("raphael lacerda atualizado", alunoAtualizado.getNome()));
                });

    }

    @Test
    public void deve_remover_alunos() {
        conferirAlunosListados(0);
        deve_salvar_aluno();
        conferirAlunosListados(1);
        web.delete().uri(URI_API_PATHS.ALUNOS_API + "/1")
                .accept(MediaType.ALL)
                .header("Authorization", TOKEN_ADMIN)
                .exchange()
                .expectStatus().isOk();
        conferirAlunosListados(0);
    }

    @Test
    public void nao_pode_salvar_aluno_se_for_estudante(){
        Aluno aluno = new Aluno("rafa");

        web.post().uri(URI_API_PATHS.ALUNOS_API)
                .header("Authorization", TOKEN_ESTUDANTE)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectStatus().isForbidden();
    }

    private void conferirAlunosListados(int total) {
        web.get().uri(URI_API_PATHS.ALUNOS_API)
                .accept(MediaType.ALL)
                .header("Authorization", TOKEN_ADMIN)
                .exchange()
                .expectStatus().isOk().expectBodyList(Aluno.class)
                .hasSize(total);
    }
}
