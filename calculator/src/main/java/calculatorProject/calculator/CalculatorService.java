package calculatorProject.calculator;

import calculatorProject.CalculatorData;
import calculatorProject.RabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class CalculatorService {

    @Autowired
    private RabbitMQService rabbitMQService;

    public void calculate(int operation,BigDecimal a,BigDecimal b){
        BigDecimal result = BigDecimal.ZERO;
        switch (operation){
            case 1:
               result = a.add(b);
                break;
            case 2:
                result = a.subtract(b);
                break;
            case 3:
                result = a.divide(b);
                break;
            case 4:
                result = a.multiply(b);
                break;
            default:
        }
        rabbitMQService.sendMessage(RabbitMQ.CALCULATOR_QUEUE,new CalculatorData(a,b,result));
    }

}
