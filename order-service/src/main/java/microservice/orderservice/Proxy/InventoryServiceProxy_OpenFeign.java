package microservice.orderservice.Proxy;

import microservice.orderservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//@FeignClient(name="inventory-service", url="localhost:8082/api/inventory") without Eureka
@FeignClient(name="inventory-service", path ="/api/inventory")
public interface InventoryServiceProxy_OpenFeign {

   @GetMapping()
   InventoryResponse[] isStock(@RequestParam List<String> skuCode);


}
