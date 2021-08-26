package calculatorProject.calculator;

import calculatorProject.RabbitMQ;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import javax.annotation.PostConstruct;

public class RabbitMQConfig {
    private AmqpAdmin amqpAdmin;

    public RabbitMQConfig(AmqpAdmin amqpAdmin){
        this.amqpAdmin=amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(RabbitMQ.CALCULATOR_EXCHANGE);
    }

    private Binding relationShip(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        //Getting exchange and queue
        Queue queueA = this.queue(RabbitMQ.CALCULATOR_QUEUE);
        DirectExchange exchangeA=this.directExchange();

        Binding link = this.relationShip(queueA,exchangeA);

        //Create queue in RabbitMQ
        this.amqpAdmin.declareQueue(queueA);
        //Create exchange in RabbitMQ
        this.amqpAdmin.declareExchange(exchangeA);

        //Links exchange-queue in RabbitMQ
        this.amqpAdmin.declareBinding(link);
    }
}
