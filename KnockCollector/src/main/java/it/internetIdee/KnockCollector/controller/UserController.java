package it.internetIdee.KnockCollector.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.internetIdee.KnockCollector.data.entity.Debtor;
import it.internetIdee.KnockCollector.data.service.DebtorService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    

    private final DebtorService debtorService;


    @PostMapping("/addAll")
    public ResponseEntity<String> addAllUsers(@RequestBody List<Debtor> entities) {
    try {
        debtorService.addAllDebtor(entities); // Assicurati che addAllDebtor esista e salvi la lista
        return ResponseEntity.ok("Debitori caricati correttamente!"); // Risposta di successo
    } catch (Exception e) {
        // Risposta di errore con messaggio dettagliato
        return ResponseEntity.internalServerError().body("Errore nel salvataggio dei debitori: " + e.getMessage());
    }

}

    
}
