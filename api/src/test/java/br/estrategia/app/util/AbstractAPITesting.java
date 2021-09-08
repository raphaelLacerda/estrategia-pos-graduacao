package br.estrategia.app.util;


import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractAPITesting {

    public static String TOKEN_ESTUDANTE;
    public static String TOKEN_ADMIN;
    @Autowired
    protected WebTestClient web;

    @BeforeAll
    public static void createTokens(){

        LoginResponse loginResponse = fazerRequisicaoPorLogin("rafa");
        TOKEN_ESTUDANTE = loginResponse.getToken();
        loginResponse = fazerRequisicaoPorLogin("admin");
        TOKEN_ADMIN = loginResponse.getToken();
    }

    public static LoginResponse fazerRequisicaoPorLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserLoginRequest> request = new HttpEntity<>(new UserLoginRequest(login, "123"));
        LoginResponse loginResponse = restTemplate.postForObject("http://localhost:3000/login", request, LoginResponse.class);
        return loginResponse;
    }

}
