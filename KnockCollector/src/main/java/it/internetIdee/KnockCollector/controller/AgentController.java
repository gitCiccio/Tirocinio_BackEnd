package it.internetIdee.KnockCollector.controller;

import it.internetIdee.KnockCollector.data.service.EmailService;
import it.internetIdee.KnockCollector.dto.AgentDTO;
import org.springframework.http.HttpStatus;
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
    private final EmailService emailService;

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

    @PostMapping("/reset")
    public ResponseEntity<String> resetAgent(@RequestBody AgentDTO agentWithOtherPass) {
        try {
            boolean otpValid = emailService.checkOtp(agentWithOtherPass.getOtp(), agentWithOtherPass.getEmail());

            if (!otpValid) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("OTP non valido o scaduto");
            }

            Agent agent = agentService.getAgentByEmail(agentWithOtherPass.getEmail());
            if (agent != null) {
                agent.setPassword(passwordEncoder.encode(agentWithOtherPass.getPassword()));
                agentService.addAgent(agent);
                return ResponseEntity.ok("Cambio password avvenuto con successo");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore durante il reset password");
        }
    }

    @PostMapping("/reset/request")
    public ResponseEntity<String> requestReset(@RequestParam String email) {
        try {
            emailService.sendMail(email);
            return ResponseEntity.ok("OTP inviato all'email " + email);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore nell'invio OTP");
        }
    }

}
