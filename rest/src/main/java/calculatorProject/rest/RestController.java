package calculatorProject.rest;

import calculatorProject.Calculator;
import calculatorProject.CalculatorData;
import calculatorProject.RabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping("/sum")
    private ResponseEntity sendSum(@RequestParam(value = "a") BigDecimal a, @RequestParam(value = "b") BigDecimal b){
        this.sendDataToCalculator(RabbitMQ.REST_QUEUE,a,b, Calculator.SUM);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("(...)");
    }
    @GetMapping("/sub")
    private  ResponseEntity sendSubtract(@RequestParam(value = "a") BigDecimal a, @RequestParam(value = "b") BigDecimal b){
        this.sendDataToCalculator(RabbitMQ.REST_QUEUE,a,b, Calculator.SUBTRACT);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("(...)");
    }
    @GetMapping("/div")
    private  ResponseEntity sendDivision(@RequestParam(value = "a") BigDecimal a, @RequestParam(value = "b") BigDecimal b){
        this.sendDataToCalculator(RabbitMQ.REST_QUEUE,a,b, Calculator.DIVIDE);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("(...)");
    }
    @GetMapping("/mult")
    private  ResponseEntity sendMultiply(@RequestParam(value = "a") BigDecimal a, @RequestParam(value = "b") BigDecimal b){
        this.sendDataToCalculator(RabbitMQ.REST_QUEUE,a,b,Calculator.MULTIPLY);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("(...)");
    }

    public void sendDataToCalculator(String queueName,BigDecimal valueA,BigDecimal valueB,int operationToPerform){
        this.rabbitMQService.sendMessage(queueName,new CalculatorData(valueA,valueB, operationToPerform));
    }



}
