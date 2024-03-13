package microservice.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Data
@AllArgsConstructor
public class OrderPlacedEvent  {
    private String orderNumber;

    private String customerMail;

    /*public OrderPlacedEvent(Object source, String orderNumber) {
        super(source);
        this.orderNumber = orderNumber;
    }

    public OrderPlacedEvent(String orderNumber) {
        super(orderNumber);
        this.orderNumber = orderNumber;
    }*/
}
