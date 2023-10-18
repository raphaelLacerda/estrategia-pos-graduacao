package br.estrategia.app.application.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//	@Autowired
//	private IDescontoGateway descontoService;

	@RequestMapping("/api/v1/hello")
	public String index() {
//		return "Olá mundo" + descontoService.aplicarDesconto(new BigDecimal(100)).getTipoDesconto();
		return "Olá mundo";
	}

}
