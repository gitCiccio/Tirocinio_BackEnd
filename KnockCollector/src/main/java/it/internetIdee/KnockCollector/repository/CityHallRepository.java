package it.internetIdee.KnockCollector.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.CityHall;

public interface CityHallRepository extends JpaRepository<CityHall,UUID>{
    
}
