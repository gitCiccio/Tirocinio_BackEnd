package it.internetIdee.KnockCollector.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Installment;
import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.repository.InstallmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstallmentService {
    
    private final InstallmentRepository installmentRepository;


    public List<Installment> getAllPracticeInstallment(Practice practiceId){
        try {
            
            return installmentRepository.findAllByPractice(practiceId);

        } catch (Exception e) {
            log.debug("Errore nel recupero delle rate.");
            return null;
        }
    }

    public Installment getPracticeInstallment(Practice practiceId){
        try {
            
            return installmentRepository.findByPractice(practiceId);

        } catch (Exception e) {
            log.debug("Errore nel recupero delle rata.");
            return null;
        }
    }

    public List<Installment> addInstallment(List<Installment> installments, Practice practice){
        List<Installment> result = new ArrayList<>();
        try {
            for(Installment installment : installments){
                installment.setPractice(practice);
                result.add(installment);
                
            }
            installmentRepository.saveAll(result);
            return result;
        } catch (Exception e) {
            log.debug("Erorre nel salvataggio della pratica");
            return null;
        }
    }

    public boolean deleteInstallment(UUID installmentId){
         try {
            installmentRepository.deleteById(installmentId);
            return true;
         } catch (Exception e) {
            log.debug("Errore nell'eliminazione della rata.");
            return false;
         }
    }

    public boolean deleteAllInstallment(Practice practice){
        try {
            List<Installment> installments = installmentRepository.findAllByPractice(practice);
            installmentRepository.deleteAll(installments);
            return true;
        } catch (Exception e) {
            log.debug("Errore nell'eliminazione delle rate.");
            return false;
        }
    }
}
