package it.internetIdee.KnockCollector.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.data.entity.Recovery;
import it.internetIdee.KnockCollector.repository.RecoveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecoveryService {
    
    private final RecoveryRepository recoveryRepository;

    public List<Recovery> getAllRecovery(Practice practice){
        try {
            return recoveryRepository.findAllByPractice(practice);

        } catch (Exception e) {
            log.debug("Errore nel caricamento dei crediti da recuperare");
            return null;
        }
    }

    public Recovery getRecovery(Practice practice){
        try {
            return recoveryRepository.findByPractice(practice);
             
        } catch (Exception e) {
            log.debug("Errore nel caricamento dei crediti da recuperare");
            return null;
        }
    }


    public List<Recovery> addRecovery(List<Recovery> recoveries, Practice practice){
        List<Recovery> result = new ArrayList<>();
        try {
            for(Recovery recovery : recoveries){
                recovery.setPractice(practice);
                result.add(recovery);
                
            }
            recoveryRepository.saveAll(result);
            return result;
        } catch (Exception e) {
            log.debug("Errore nell'aggiunta del recupero");
            return null;
        }
    }

    public boolean deleteRecovery(UUID recoveryId){
        try {
            recoveryRepository.deleteById(recoveryId);
            return true;
        } catch (Exception e) {
            log.debug("Errore nella cancellazione del recupero");
            return false;
        }
    }

    //Elimina tutti i recuperi
    public boolean deleteAllRecovery(Practice practice){
        try {
            List<Recovery> recoveries = recoveryRepository.findAllByPractice(practice);
            recoveryRepository.deleteAll(recoveries);
            return true;
        }catch (Exception e){
            log.debug("Errore nella cancellazione dei recuperi");
            return false;
        }
    }
}
