package it.internetIdee.KnockCollector.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Installment;
import it.internetIdee.KnockCollector.data.entity.Practice;

import java.util.List;


public interface InstallmentRepository extends JpaRepository<Installment, UUID>{
    
    public List<Installment> findAllByPractice(Practice practiceId);

    public Installment findByPractice(Practice practiceId);
}
