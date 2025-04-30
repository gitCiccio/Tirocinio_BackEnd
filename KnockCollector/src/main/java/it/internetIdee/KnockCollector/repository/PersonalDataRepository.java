package it.internetIdee.KnockCollector.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.PersonalData;


public interface PersonalDataRepository extends JpaRepository<PersonalData,UUID>{

   public Optional<PersonalData> findByTaxCode(String taxCode);
}
