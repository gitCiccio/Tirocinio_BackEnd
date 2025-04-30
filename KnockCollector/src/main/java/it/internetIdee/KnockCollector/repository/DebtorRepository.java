package it.internetIdee.KnockCollector.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Debtor;
import it.internetIdee.KnockCollector.data.entity.PersonalData;




public interface DebtorRepository extends JpaRepository<Debtor,UUID>{
    
    public Optional<Debtor> findByPersonalData(PersonalData personalData);

} 
