package it.internetIdee.KnockCollector.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Agent;


public interface AgentRepository extends JpaRepository<Agent, UUID>{
    
    public Agent findByEmail(String email);

    public List<Agent> findAllByEmail(String email);
}
