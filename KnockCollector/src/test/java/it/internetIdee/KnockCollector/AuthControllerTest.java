package it.internetIdee.KnockCollector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.internetIdee.KnockCollector.controller.AuthController;
import it.internetIdee.KnockCollector.data.entity.Agent;
import it.internetIdee.KnockCollector.data.service.AgentService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AgentService agentService;

    @InjectMocks
    private AuthController authController;

    private Agent agent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Crea un oggetto agent con dati di test
        agent = new Agent();
        agent.setEmail("test@email.com");
        agent.setPassword("password123");
        agent.setRole("collector");
    }

    @Test
    public void testLogin_Success() throws Exception {
        // Simula il comportamento del service
        when(agentService.getAgentByEmail("ciccio@gmail.com")).thenReturn(agent);

        // Esegui il test chiamando l'endpoint login
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"ciccio@gmail.com\",\"password\":\"myPass10\"}"))
                .andExpect(status().isOk()) // Verifica che lo stato della risposta sia 200 OK
                .andExpect(jsonPath("$.email").value("ciccio@gmail.com")) // Verifica che l'email sia corretta
                .andExpect(jsonPath("$.role").value("agent")); // Verifica che il ruolo sia corretto
    }

    @Test
    public void testLogin_Failure() throws Exception {
        // Simula il comportamento del service
        when(agentService.getAgentByEmail("test@email.com")).thenReturn(null);

        // Esegui il test chiamando l'endpoint login con credenziali sbagliate
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@email.com\",\"password\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized()); // Verifica che lo stato della risposta sia 401 Unauthorized
    }
}
