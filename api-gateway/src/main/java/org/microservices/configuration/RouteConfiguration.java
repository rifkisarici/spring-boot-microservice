package org.microservices.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {
    //Özel route lar olustiumak icin
    //Bean tanimlayarak route lar tanimlanir

    /*#Product Service for pom.xml
    spring.cloud.gateway.routes[0].id=product-service
    spring.cloud.gateway.routes[0].uri=lb://product-service
    spring.cloud.gateway.routes[0].predicates[0]=Path=/api/product*/

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder){
            //return routeLocatorBuilder.routes().build();

        //routlar tanimlandi bir root listesi yapildi.
        return routeLocatorBuilder.routes()
               /* .route("product-service", p -> p              //  route tanimlanir
                        .path("/api/product")                   //path ile isteğin path'ine gore eslestirme yapilir
                        .uri("lb://product-service"))              //uri ile isteğin yonlendirileceği hedef belirtilir. lb: yuk dengeleme yapildiği belirtilmistir.
*/
                .route("product-service", p -> p
                        .path("/api/product/**")
                        .filters(f -> f.rewritePath("/(?<segment>.*)", "/${segment}"))
                        .uri("lb://product-service"))

                .route("order-service", p -> p
                        .path("/api/order/**")
                        .filters(f -> f.rewritePath("/(?<segment>.*)", "/${segment}"))
                        .uri("lb://order-service"))



                /*.route("order-service", p -> p
                        .path("/api/order2")         //eğer patha oreder2 yazarsa doğru adres gonderir
                        .filters(f -> f.rewritePath("/api/order2", "/api/order"))
                        .uri("lb://order-service"))*/

                .route("discovery-service", p -> p
                        .path("/eureka/**")
                        .filters(f -> f.rewritePath("/eureka(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8761"))

                /*.route("discovery-server", r -> r
                        .path("/eureka/")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761")
                )*/




                .build();
    }

}
