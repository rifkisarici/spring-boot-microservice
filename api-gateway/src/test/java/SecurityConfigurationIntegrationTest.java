import org.junit.jupiter.api.Test;
import org.microservices.configuration.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = SecurityConfiguration.class)
public class SecurityConfigurationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void testPublicEndpointAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/eureka/**"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void testAuthenticatedEndpointAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/secured"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
