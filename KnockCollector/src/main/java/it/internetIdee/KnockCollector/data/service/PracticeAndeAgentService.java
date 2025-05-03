package it.internetIdee.KnockCollector.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.PracticeAndAgent;
import it.internetIdee.KnockCollector.repository.PracticeAndAgentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticeAndeAgentService {
    
    private final PracticeAndAgentRepository practiceAndAgentRepository;

    public List<UUID> getPracticesId(UUID agentid){
        try {
            List<UUID> practiceAndAgentList = new ArrayList<>();
            for(PracticeAndAgent pAndA : practiceAndAgentRepository.findAllByAgent(agentid)){
                practiceAndAgentList.add(pAndA.getPractice());
            }
            return practiceAndAgentList;
        } catch (Exception e) {
            log.debug("Errore nel recupero degli id delle pratiche");
            return null;
        }
    }

    public PracticeAndAgent addAgentAndPractice(PracticeAndAgent practiceAndAgent){
        try {
            
            return practiceAndAgentRepository.save(practiceAndAgent);
        } catch (Exception e) {
            log.debug("Errore nel caricamento dei dati della relazione");
            return null;
        }
    }

    public Boolean deletePracticeAndAgent(UUID practiceId){
        try {
             practiceAndAgentRepository.deleteById(practiceId);
            return true;
        }catch (Exception e){
            log.debug("Errore nella rimozione dell'associazione pratica/agente");
            return false;
        }
    }
}
