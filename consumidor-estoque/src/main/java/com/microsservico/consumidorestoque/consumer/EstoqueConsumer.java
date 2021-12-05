package com.microsservico.consumidorestoque.consumer;

import constantes.RabbitmqConstantes;
import dto.EstoqueDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitmqConstantes.FILA_ESTOQUE)
    private void consumidor(EstoqueDTO estoqueDTO){
        System.out.println(estoqueDTO.codigoproduto);
        System.out.println(estoqueDTO.quantidade);
        System.out.println("----------------------------------");
    }
}
