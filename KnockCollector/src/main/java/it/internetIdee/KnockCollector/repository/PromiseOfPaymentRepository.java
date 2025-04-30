package it.internetIdee.KnockCollector.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.data.entity.PromiseOfPayment;

public interface PromiseOfPaymentRepository extends JpaRepository<PromiseOfPayment, UUID>{
    
    public List<PromiseOfPayment> findAllByPractice(Practice practice);

    public PromiseOfPayment findByPractice(Practice practice);
}
