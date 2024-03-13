package microservice.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @Retry(name = "inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    //@TimeLimiter(name = "inventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(orderRequest);
    }

    public String fallbackMethod(Exception exception){
        return "Oops! An error occurred. Please order after some time!";
    }

}
