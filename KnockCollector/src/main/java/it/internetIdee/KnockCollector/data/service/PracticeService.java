package it.internetIdee.KnockCollector.data.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Agent;
import it.internetIdee.KnockCollector.data.entity.Debtor;
import it.internetIdee.KnockCollector.data.entity.Installment;
import it.internetIdee.KnockCollector.data.entity.Note;
import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.data.entity.PracticeAndAgent;
import it.internetIdee.KnockCollector.data.entity.PromiseOfPayment;
import it.internetIdee.KnockCollector.data.entity.Recovery;
import it.internetIdee.KnockCollector.repository.PracticeRepository;
import it.internetIdee.KnockCollector.utils.AmountUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticeService {

    private final PracticeRepository practiceRepository;
    private final DebtorService debtorService;
    private final InstallmentService installmentService;
    private final PromiseOfPaymentService promiseOfPaymentService;
    private final RecoveryService recoveryService;
    private final AgentService agentService;
    private final PracticeAndeAgentService practiceAndeAgentService;
    private final NoteService noteService;

    //Fai la presa delle pratiche
    @Transactional
    public Practice addPractice(Practice practice){
        try {
            Debtor debtorFound = debtorService.getDebtorByTaxCode(practice.getDebtor().getPersonalData());
            List<Installment> tempInstallment = practice.getInstallment();
            List<PromiseOfPayment> tempPromiseOfPayments = practice.getPromiseOfPayment();
            List<Recovery> tempRecovery = practice.getRecovery();
            List<Note> tempNotes = practice.getNotes();

            if(AmountUtils.isValidAmount(practice.getCreditToRecover()) && debtorFound!=null){
                practice.setDebtor(debtorFound);
                practice.setInstallment(new ArrayList<>());
                practice.setRecovery(new ArrayList<>());
                practice.setPromiseOfPayment(new ArrayList<>());
                Practice savedPractice = practiceRepository.save(practice);
                
                debtorService.addDebtor(debtorFound);
                savedPractice.setInstallment(installmentService.addInstallment(tempInstallment, savedPractice));
                savedPractice.setPromiseOfPayment(promiseOfPaymentService.addPromise(tempPromiseOfPayments, savedPractice));
                savedPractice.setRecovery(recoveryService.addRecovery(tempRecovery, savedPractice));
                savedPractice.setNotes(noteService.addNote(tempNotes, savedPractice));
                

                return savedPractice;
            }
            return null;
        } catch (Exception e) {
            log.debug("Errore nel salvataggio della pratica");
            return null;
        }
    }

    @Transactional
    public PracticeAndAgent addPracticeAndAgent(PracticeAndAgent practiceAndAgent){
        try {
            return practiceAndeAgentService.addAgentAndPractice(practiceAndAgent);
        } catch (Exception e) {
            log.debug("Erorre nel caricamento della relazione tra pratiche e agente");
            return null;
        }
    }

    @Transactional
    public Practice addAllPractice(List<Practice> practices){
        try {
            for(Practice practice : practices){
                Debtor debtorFound = debtorService.getDebtorByTaxCode(practice.getDebtor().getPersonalData());
                List<Installment> tempInstallment = practice.getInstallment();
                List<PromiseOfPayment> tempPromiseOfPayments = practice.getPromiseOfPayment();
                List<Recovery> tempRecovery = practice.getRecovery();

                if(AmountUtils.isValidAmount(practice.getCreditToRecover()) && debtorFound!=null){
                    practice.setDebtor(debtorFound);
                    practice.setInstallment(new ArrayList<>());
                    practice.setRecovery(new ArrayList<>());
                    practice.setPromiseOfPayment(new ArrayList<>());
                    Practice savedPractice = practiceRepository.save(practice);
                    
                    debtorService.addDebtor(debtorFound);
                    savedPractice.setInstallment(installmentService.addInstallment(tempInstallment, savedPractice));
                    savedPractice.setPromiseOfPayment(promiseOfPaymentService.addPromise(tempPromiseOfPayments, savedPractice));
                    savedPractice.setRecovery(recoveryService.addRecovery(tempRecovery, savedPractice));
                    

                    return savedPractice;
                }
            }
            return null;
        } catch (Exception e) {
            log.debug("Errore nel salvataggio della pratica");
            return null;
        }
    }

    public Practice getPractice(UUID practice){
        try {
            Optional<Practice> optionalPractice = practiceRepository.findById(practice);
            if(optionalPractice.isPresent())
                return optionalPractice.get();
            return null;
        } catch (Exception e) {
            log.debug("Errore nel recupero della pratica");
            return null;
        }
    }

    public List<Practice> getPracticeByDebtor(UUID debtorId){
        try {
            Debtor debtor = debtorService.getDebtor(debtorId);
            System.out.println("debitore trovato: "+debtor.getEmail());
            List<Practice> practices = practiceRepository.findAllByDebtor(debtor);
            if(practices != null){
                System.out.println("Lista pratiche nulla");
            }else{
                System.out.println("Lista pratiche non nulla");
            }
            return practices;
        } catch (Exception e) {
            log.debug("Errore nel recupero della pratica");
            return null;
        }
    }

    public List<Practice> getPracticeByAgent(UUID agentId){
        try {
            Agent agent = agentService.getAgentById(agentId);
            List<UUID> practicesId = practiceAndeAgentService.getPracticesId(agent.getAgentId());
            List<Practice> agentPractices = new ArrayList<>();
            for(UUID practiceId : practicesId){
                Optional<Practice> optionalPractice = practiceRepository.findById(practiceId);
                if(optionalPractice.isPresent())
                    agentPractices.add(optionalPractice.get());
            }
            return agentPractices;
        } catch (Exception e) {
            log.debug("Errore nel recupero della pratica");
            return null;
        }
    }
}
