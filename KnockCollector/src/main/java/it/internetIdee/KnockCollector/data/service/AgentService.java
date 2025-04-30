package it.internetIdee.KnockCollector.data.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Agent;
import it.internetIdee.KnockCollector.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentService {
    
    private final AgentRepository agentRepository;


    public Agent getAgentByEmail(String email){
        try {
            Agent agent = agentRepository.findByEmail(email);
            return agent;
        } catch (Exception e) {
            log.debug("Errore nel recupero dei dati dell'agente.");
            return null;
        }
    }

    public Agent getAgentById(UUID agentId){
        try {
            Optional<Agent> agent = agentRepository.findById(agentId);
            if(agent.isPresent())
                return agent.get();
            return null;
        } catch (Exception e) {
            log.debug("Errore nel recupero dei dati dell'agente.");
            return null;
        }
    }


    public Agent addAgent(Agent agent){
        try {
            return agentRepository.save(agent);
        } catch (Exception e) {
            log.debug("Errore nell'inserimento dell'agente");
            return null;
        }
    }
}
