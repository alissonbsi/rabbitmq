package estoquepreco.connections;

import constantes.RabbitmqConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitmqConection {

    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitmqConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    // Método para criação de filas
    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    // Método para criação das exchanges
    private DirectExchange trocadireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    // Método para relacionar as filas as exchanges
    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }


    // Método responsável por utilizar as funções criadas acima e criar as filas dentro do RabbitMQ
    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(RabbitmqConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitmqConstantes.FILA_PRECO);

        DirectExchange troca = this.trocadireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = this.relacionamento(filaPreco, troca);

        //Crianda as filas do RabbitMQ
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);

    }
}
