package microservice.orderservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import microservice.orderservice.dto.OrderLineItemsDTO;
import microservice.orderservice.dto.OrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Container
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");
	static {
		postgreSQLContainer.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}


	//@Transactional// eğer container kullnmasaydik; service de create yapilirken Transactional kullanildiği icin, "Order"i gercek veri tabanina kaydeiliyorudu. bunu onledi
	@Test
	void shouldCreateOrder() throws Exception {
		OrderLineItemsDTO orderLineItemsDTO=new OrderLineItemsDTO();
		orderLineItemsDTO.setPrice(BigDecimal.valueOf(1500));
		orderLineItemsDTO.setSkuCode("nokia");
		orderLineItemsDTO.setQuantity(1);

		List<OrderLineItemsDTO> orderLineItemsDTOList =new ArrayList<>();
		orderLineItemsDTOList.add(orderLineItemsDTO);
		OrderRequest orderRequest=new OrderRequest();
		orderRequest.setOrderLineItemsDTOList(orderLineItemsDTOList);
		String orderRequestString=objectMapper.writeValueAsString(orderRequest);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(orderRequestString))
				.andExpect(status().isCreated());
	}


}
