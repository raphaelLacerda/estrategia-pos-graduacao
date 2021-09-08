package br.estrategia.app.application.resource;

import br.estrategia.app.infra.rest.URI_API_PATHS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerTest {
    @Autowired
    private WebTestClient web;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(3000)).build();
    }

    @Test
    public void deve_estar_ready() {


        web.get().uri(URI_API_PATHS.READNIESS).accept(MediaType.ALL)
                .exchange().expectStatus().isOk().expectBody(String.class).value(resposta -> {
            Assertions.assertTrue(resposta.contains("ok"));
        });
    }

    @Test
    public void deve_estar_live() {


        web.get().uri(URI_API_PATHS.LIVENESS).accept(MediaType.ALL)
                .exchange().expectStatus().isOk().expectBody(String.class).value(resposta -> {
            Assertions.assertTrue(resposta.contains("live"));
        });
    }
}
