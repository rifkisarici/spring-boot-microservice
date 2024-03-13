package microservice.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)// otomatik OrderLineItems yaratmak icin.
    private List<OrderLineItems> orderLineItemsList;

    private String customerMail;

    /*
    "CascadeType.ALL", JPA (Java Persistence API) icinde iliskili nesnelerin omruyle ilgili olarak bir
    "cascade" islemi tanimlar. Bu islem, bir ana nesnenin hayat dongusu boyunca iliskili nesnelerin de
    hayat dongusunu yonetmeyi sağlar."ALL" seceneği, tum islemleri (persist, merge, remove, refresh) kapsar.

    Parent nesnesi persist edildiğinde Child nesnesi de otomatik olarak persist edilir,
    OrderLineItems entity si icin ayrica bir Repository olusturmadik.
    */



}
