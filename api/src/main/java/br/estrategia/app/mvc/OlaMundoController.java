package br.estrategia.app.mvc;

import br.estrategia.app.domain.model.entidade.Aluno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class OlaMundoController {

	@GetMapping("/oi-mvc")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);

		List<Aluno> alunos = Arrays.asList(new Aluno("rafa"), new Aluno("node"), new Aluno("pepe"));
		model.addAttribute("alunos", alunos);
		return "hello-mvc";
	}
}