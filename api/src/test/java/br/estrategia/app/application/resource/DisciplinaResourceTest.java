package br.estrategia.app.application.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


//TODO: Atividade extra!!!! Faça os testes para salvar disciplina e professores
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DisciplinaResourceTest {
    @Autowired
    private WebTestClient web;
}
