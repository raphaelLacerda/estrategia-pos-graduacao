package br.estrategia.app.application.resource;

import br.estrategia.app.domain.model.entidade.Aluno;
import br.estrategia.app.domain.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@Disabled
@ExtendWith(MockitoExtension.class)
class AlunoResourceUnitTest {

    @InjectMocks
    private AlunoResource alunoResource;

    @Mock
    private AlunoRepository alunoRepository;


    @BeforeEach
    public void setUp() {
        Mockito.when(alunoRepository.findAll())
                .thenReturn(Arrays.asList(new Aluno("rafa"), new Aluno("matheus")));
    }

//    @Test
//    public void deve_listar_alunos(){
//        ResponseEntity<List<Aluno>> alunos =
//                alunoResource.listar();
//
//        Assertions.assertEquals(200, alunos.getStatusCode().value());
//        Assertions.assertEquals(2, alunos.getBody().size());
//
//    }
}
