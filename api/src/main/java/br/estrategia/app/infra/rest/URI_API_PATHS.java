package br.estrategia.app.infra.rest;

public class URI_API_PATHS {

    private static final String API = "/api";
    private static final String VERSAO = "/v1";


    public static final String READNIESS = "/monitor/readiness";
    public static final String LIVENESS = "/monitor/liveness";
    public static final String ACTUATOR = "/actuator";
    public static final String ALUNOS_API = API + VERSAO +"/alunos";
    public static final String PROFESSORES_API = API + VERSAO +"/professores";
    public static final String DISCIPLINAS_API = API + VERSAO +"/disciplinas";
    public static final String CONCURSOS_API = API + VERSAO +"/concursos";
    public static final String MATRICULAS_API = API + VERSAO +"/matriculas";
}
