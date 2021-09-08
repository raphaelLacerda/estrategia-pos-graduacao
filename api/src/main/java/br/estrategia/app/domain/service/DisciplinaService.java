package br.estrategia.app.domain.service;

import br.estrategia.app.domain.excecao.ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException;
import br.estrategia.app.domain.model.entidade.Disciplina;
import br.estrategia.app.domain.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    public Disciplina save(Disciplina disciplina){

        if(disciplina.possuiProfessor()){
            Set<Disciplina> disciplinasDoProfessor = disciplinaRepository.findByProfessor(disciplina.getProfessor());
            if(disciplinasDoProfessor.size()>=2){
                throw new ProfessorNaoPodeMinistrarMaisDeDuasDisciplinasException();
            }
        }

        return disciplinaRepository.save(disciplina);
    }


    public DisciplinaRepository getDisciplinaRepository() {
        return disciplinaRepository;
    }
}
