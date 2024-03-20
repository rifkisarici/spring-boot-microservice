package com.microservice.inventoryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryServiceIntegrationTests {

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

	@Test
	void isStock() throws Exception{
		List<String> list=List.of("skuCode");
		String requestString=objectMapper.writeValueAsString(list);
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/inventory")
						.param("skuCode", requestString)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
