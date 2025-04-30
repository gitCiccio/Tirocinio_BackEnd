package it.internetIdee.KnockCollector.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.internetIdee.KnockCollector.data.entity.Agent;
import it.internetIdee.KnockCollector.data.service.AgentService;
import it.internetIdee.KnockCollector.data.service.JwtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AgentService agentService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Agent agentRequest) {
        try {
            // Autenticazione con AuthenticationManager
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(agentRequest.getEmail(), agentRequest.getPassword())
            );

            // Carica agente e genera token
            Agent agent = agentService.getAgentByEmail(agentRequest.getEmail());
            String jwtToken = jwtService.generateToken(agent);

            // Ritorna token e ruolo
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("role", agent.getRole());
            response.put("email", agent.getEmail());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "Credenziali non valide")
            );
        }
    }
}
