package br.estrategia.app.application.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/ola")
	public String index() {
		return "Olá mundo";
	}

}
