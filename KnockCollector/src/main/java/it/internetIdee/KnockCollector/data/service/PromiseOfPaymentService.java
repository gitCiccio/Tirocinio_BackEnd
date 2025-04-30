package it.internetIdee.KnockCollector.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.data.entity.PromiseOfPayment;
import it.internetIdee.KnockCollector.repository.PromiseOfPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromiseOfPaymentService {
    
    private final PromiseOfPaymentRepository promiseOfPaymentRepository;

    public List<PromiseOfPayment> getAllPromise(Practice practice){
        try {
            return promiseOfPaymentRepository.findAllByPractice(practice);
        } catch (Exception e) {
            log.debug("Errore nel caricamento delle promesse di pagamento");
            return null;
        }
    }

    public PromiseOfPayment getPromise(Practice practice){
        try {
            return promiseOfPaymentRepository.findByPractice(practice);
        } catch (Exception e) {
            log.debug("Errore nel caricamento delle promesse di pagamento");
            return null;
        }
    }


    public List<PromiseOfPayment> addPromise(List<PromiseOfPayment> promiseOfPayments, Practice practice){
        List<PromiseOfPayment> result = new ArrayList<>();
        try {
            for(PromiseOfPayment promise : promiseOfPayments){
                promise.setPractice(practice);
                result.add(promise);
                
            }
            promiseOfPaymentRepository.saveAll(result);
            return result;
        } catch (Exception e) {
            log.debug("Errore nel salvataggio della promessa");
            return null;
        }
    }


    public boolean deletePromise(UUID promiseId){
        try {
            promiseOfPaymentRepository.deleteById(promiseId);
            return true;
        } catch (Exception e) {
            log.debug("Errore nella cancellazione della promessa");
            return false;
        }
    }

}
