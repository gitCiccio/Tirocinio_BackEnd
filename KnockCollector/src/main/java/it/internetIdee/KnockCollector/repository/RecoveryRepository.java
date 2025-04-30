package it.internetIdee.KnockCollector.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.data.entity.Recovery;

public interface RecoveryRepository extends JpaRepository<Recovery, UUID>{
    
    public List<Recovery> findAllByPractice(Practice practice);

    public Recovery findByPractice(Practice practice);
}
