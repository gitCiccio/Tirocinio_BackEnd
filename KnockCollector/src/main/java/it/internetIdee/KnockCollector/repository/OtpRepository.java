package it.internetIdee.KnockCollector.repository;

import it.internetIdee.KnockCollector.data.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {

    Optional<Otp> findOtpByUserEmail(String email);
}
