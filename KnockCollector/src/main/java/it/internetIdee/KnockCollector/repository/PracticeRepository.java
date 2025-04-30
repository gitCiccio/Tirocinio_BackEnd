package it.internetIdee.KnockCollector.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Debtor;
import it.internetIdee.KnockCollector.data.entity.Practice;
import java.util.List;


public interface PracticeRepository extends JpaRepository<Practice, UUID>{
    
    public Practice findByDebtor(Debtor debtor);

    public List<Practice> findAllByDebtor(Debtor debtor);
}
