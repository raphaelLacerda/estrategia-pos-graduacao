package br.estrategia.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
//@CucumberOptions(
//        features = {
//                "classpath:features/aplicar_desconto_em_concurso.feature",
//                "classpath:features/is_it_friday_yet.feature",
//                "classpath:features/cadastro_disciplina.feature"
//        },
//        glue = {"br.estrategia.app"})
public class RunCucumberTest {

}