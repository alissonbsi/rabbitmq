package estoquepreco.controller;

import constantes.RabbitmqConstantes;
import dto.EstoqueDTO;
import estoquepreco.service.RabbitmqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstoqueController.class);

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDTO estoqueDTO){

        LOGGER.info("Código Produto: " + estoqueDTO.codigoproduto );
        this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_ESTOQUE, estoqueDTO);

        return new ResponseEntity(HttpStatus.OK);

    }
}
