package it.internetIdee.KnockCollector.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.data.entity.PracticeAndAgent;
import it.internetIdee.KnockCollector.data.service.PracticeService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/practice")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PracticeController {

    private final PracticeService practiceService;
    

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addPractice(@RequestBody Practice entity) {
        try {
            practiceService.addPractice(entity);
            return ResponseEntity.ok().body("Pratica aggiunta con successo");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore nell'aggiunta della pratica");
        }
    }

    @PostMapping("/add/relationship")
    public ResponseEntity<String> addPRacticeRelationship(@RequestBody PracticeAndAgent practiceAndAgent) {
        try {
            practiceService.addPracticeAndAgent(practiceAndAgent);
            return ResponseEntity.ok().body("Relazione aggiunta con successo");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore nell'aggiunta della relazione");
        }
    }
    
    
    @GetMapping("/practices/debtor")
    public List<Practice> getAllPracticesByDebtor(@RequestParam UUID debtorId) {
        try {
            return practiceService.getPracticeByDebtor(debtorId);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/practices/agent")
    public List<Practice> getAgentPractice(@RequestParam UUID agentId) {
        System.out.println("Sono nella get delle pratiche tramite l'id dell'agente");
        try {
            return practiceService.getPracticeByAgent(agentId);
        } catch (Exception e) {
            return null;
        }
    }


    
    //Visualizzazione lato front
    //Salvataggio della pratica
    //Lato admin, creazione pratiche, assegnazione pratiche e rimozione pratiche
}
