package calculatorProject.calculator;

import calculatorProject.CalculatorData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendMessage(String queueName, CalculatorData message){
        this.rabbitTemplate.convertAndSend(queueName,message);
    }
}
