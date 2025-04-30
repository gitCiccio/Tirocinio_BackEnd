package it.internetIdee.KnockCollector.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Address;
import it.internetIdee.KnockCollector.data.entity.Debtor;

import java.util.List;




public interface AddressRepository extends JpaRepository<Address, UUID>{
    
    public Optional<Address> findByHouseNumber(String houseNumber);

    public List<Address> findByDebtor(Debtor debtor);
}
