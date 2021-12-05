package estoquepreco.controller;


import constantes.RabbitmqConstantes;
import dto.PrecoDTO;
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
@RequestMapping(value = "preco")
public class PrecoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrecoController.class);

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity alteraPreco(@RequestBody PrecoDTO precoDTO){

        LOGGER.info("Código Produto: " + precoDTO.codigoproduto );

        this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_PRECO, precoDTO);

        return new ResponseEntity(HttpStatus.OK);

    }
}
