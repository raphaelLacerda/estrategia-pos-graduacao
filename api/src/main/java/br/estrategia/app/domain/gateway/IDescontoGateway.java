package br.estrategia.app.domain.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name="calculadora-descontos")
public interface IDescontoGateway {

    @GetMapping(value = "/descontos/{valor}", consumes = "application/json")
    DescontoDTO aplicarDesconto(@PathVariable("valor") BigDecimal valor);

    @GetMapping(value = "/ola")
    String ping();
}
