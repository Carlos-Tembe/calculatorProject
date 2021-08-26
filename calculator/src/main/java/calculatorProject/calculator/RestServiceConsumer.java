package calculatorProject.calculator;

import calculatorProject.CalculatorData;
import calculatorProject.RabbitMQ;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestServiceConsumer {

    @Autowired
    private  CalculatorService calculatorService;

    @RabbitListener(queues = RabbitMQ.REST_QUEUE)
    private void consumer(CalculatorData calculator){
       calculatorService.calculate(calculator.operation,calculator.a,calculator.b);
    }
}
