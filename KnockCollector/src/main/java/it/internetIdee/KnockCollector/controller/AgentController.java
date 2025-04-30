package it.internetIdee.KnockCollector.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.internetIdee.KnockCollector.data.entity.Agent;
import it.internetIdee.KnockCollector.data.service.AgentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AgentController {
    

    private final AgentService agentService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public ResponseEntity<String> addAgent(@RequestBody Agent entity) {
        try {
            
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            agentService.addAgent(entity);
            return ResponseEntity.ok().body("Registrazione avvenuta con successo");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore nella registrazione");
        }
    }

    @GetMapping("/agent")
    public Agent getAgentDataByEmail(@RequestParam String email) {
        try {
            return agentService.getAgentByEmail(email);
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
