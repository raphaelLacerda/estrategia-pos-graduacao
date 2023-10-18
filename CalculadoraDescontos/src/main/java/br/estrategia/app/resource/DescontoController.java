package br.estrategia.app.resource;

import br.estrategia.app.model.Desconto;
import br.estrategia.app.model.TipoDesconto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RefreshScope
public class DescontoController{

    @Value("${tipo.desconto}")
    TipoDesconto tipoDesconto = TipoDesconto.NORMAL;

    @GetMapping(value = "/descontos/{valor}", produces = "application/json")
    public ResponseEntity<Desconto> aplicarDesconto(@PathVariable("valor") BigDecimal valor) {
        System.out.println("CHEGGADNOO CALCULADO DE DESCONTO");
        return new ResponseEntity<>(new Desconto(tipoDesconto, valor), HttpStatus.OK);
    }


    @GetMapping(value = "/ola")
    public String ping() {

        System.out.println("CHEGGADNOO ola");
        return "OIII - RAPHAEL"+ tipoDesconto;
    }
}
