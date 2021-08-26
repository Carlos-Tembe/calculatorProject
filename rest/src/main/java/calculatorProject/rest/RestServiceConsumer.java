package calculatorProject.rest;

import calculatorProject.CalculatorData;
import calculatorProject.RabbitMQ;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RestServiceConsumer {

    @RabbitListener(queues = RabbitMQ.CALCULATOR_QUEUE)
    private void consumer(CalculatorData calculator){
        System.out.println("result = "+ calculator.result);
    }
}
