package br.estrategia.app.domain.service;

import br.estrategia.app.domain.model.entidade.Matricula;
import br.estrategia.app.domain.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;


    /**
     * https://dzone.com/articles/running-on-time-with-springs-scheduled-tasks
     */
    @Scheduled(cron = "00 59 23 * * *", zone = "GMT-3:00")
    public void naoPermitirMaisAcessoDoAlunoAposDataDaProva() {

        Set<Matricula> matriculasQueDevemSerInativadas =
                matriculaRepository.listarMatriculasDeConcursosComDataDaProvaMenorQue(LocalDate.now());

        matriculasQueDevemSerInativadas.forEach(m-> matriculaRepository.save(m.inativarAcesso()));
    }

    public MatriculaRepository getMatriculaRepository() {
        return matriculaRepository;
    }
}
