package it.internetIdee.KnockCollector.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.PracticeAndAgent;
import java.util.List;


public interface PracticeAndAgentRepository extends JpaRepository<PracticeAndAgent,UUID>{
    
    public List<PracticeAndAgent> findAllByAgent(UUID agentId);
}
