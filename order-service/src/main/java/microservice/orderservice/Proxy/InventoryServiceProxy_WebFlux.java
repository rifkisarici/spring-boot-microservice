package microservice.orderservice.Proxy;

import microservice.orderservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class InventoryServiceProxy_WebFlux {
    private final WebClient.Builder webClient;

    public InventoryServiceProxy_WebFlux(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://inventory-service/api/inventory");
    }

    public InventoryResponse[] isStock(List<String> skuCodes) {
        return webClient.build().get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("skuCode", skuCodes)
                        .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class).block();
    }

}
