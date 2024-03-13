package com.microservice.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productservice.application.command.ProductCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Container
	//static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	static {
		mongoDBContainer.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}


	//Fonksiyonel Test
	@Test
	void shouldCreateProduct() throws Exception {
		ProductCommand productCommand = ProductCommand.builder()
										.name("testssrc")
										.description("sasrc")
										.price("123")
										.build();
		String productCommandString=objectMapper.writeValueAsString(productCommand);

		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productCommandString))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldGetAllProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/product")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}



}
