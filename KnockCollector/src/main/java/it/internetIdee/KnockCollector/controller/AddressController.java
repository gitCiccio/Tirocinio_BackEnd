package it.internetIdee.KnockCollector.controller;

import it.internetIdee.KnockCollector.data.entity.Address;
import it.internetIdee.KnockCollector.data.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    
    private final AddressService addressService;
    
    @PostMapping("/add")
    public ResponseEntity<String> addAddress(@RequestBody Address entity) {
    try {
        addressService.addAddress(entity);
        return ResponseEntity.ok("Indirizzo caricato correttamente!");
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Errore nel salvataggio dell'indirizzo!");}
    }

    @PostMapping("/addAll")
    public ResponseEntity<String> addAllAddress(@RequestBody List<Address> entities) {
    try {
        addressService.addAll(entities);
        return ResponseEntity.ok("Indirizzi caricati correttamente!");
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Errore nel salvataggio dell'indirizzo!");}
    }

    
}
