package it.internetIdee.KnockCollector.data.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Address;
import it.internetIdee.KnockCollector.data.entity.Debtor;
import it.internetIdee.KnockCollector.data.entity.PersonalData;
import it.internetIdee.KnockCollector.repository.DebtorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Capire perché gli id_debitori non sono salvati negli indirizzi
@Service
@RequiredArgsConstructor
@Slf4j
public class DebtorService {
    
    private final DebtorRepository debtorRepository;
    private final PersonalDataService personalDataService;
    private final AddressService addressService;

    public List<Debtor> addAllDebtor(List<Debtor> debtors){
        
        //Prima cosa da fare, scandire i debitori
        //Salvare i loro dati personali
        for(Debtor debtor: debtors){
            PersonalData savedPersonalData = personalDataService.addPersonalData(debtor.getPersonalData());
            //Settare i dati personali per prendere il riferimento
            debtor.setPersonalData(savedPersonalData);
            Debtor savedDebtor = debtorRepository.save(debtor);

            //Ora del debitore dobbiamo prendere i suoi indirizzi
            //Quindi bisogna prendere gli indirizzi
            List<Address> addresses = addressService.findaAllAddresses();

            //Ora da qua dobbiamo trovare quali sono gli indirizzi di un certo debitore e settare il riferimento

            for(Address address : addresses){
                for(Address debAddress : savedDebtor.getAddresses()){
                    if(address.getHouseNumber().equals(debAddress.getHouseNumber())){
                        address.setDebtor(savedDebtor);
                    }
                }
            }
            addressService.addAll(addresses);
        }

        return debtorRepository.findAll();
    }

    public Debtor addDebtor(Debtor debtor){

        PersonalData savedPersonalData = personalDataService.addPersonalData(debtor.getPersonalData());
        debtor.setPersonalData(savedPersonalData);

        Debtor savedDebtor = debtorRepository.save(debtor);
        
        List<Address> addresses = addressService.findaAllAddresses();

        for(Address address : addresses){
            for(Address debAddress : savedDebtor.getAddresses()){
                if(address.getHouseNumber().equals(debAddress.getHouseNumber())){
                    address.setDebtor(savedDebtor);
                }
            }
        }
        addressService.addAll(addresses);
        
        return savedDebtor;
    }

    public Debtor getDebtor(UUID debtor){
        try {
            Optional<Debtor> optionalDebtor = debtorRepository.findById(debtor);
            if(optionalDebtor.isPresent())
                return optionalDebtor.get();
            return null;

        } catch (Exception e) {
            log.debug("Debitore non trovato");
            return null;
        }
    }

    public Debtor getDebtorByTaxCode(PersonalData personalData){
        try {
            PersonalData optionalPersonalData = personalDataService.getPersonalDataByTaxCode(personalData.getTaxCode());
            Optional<Debtor> optionalDebtor = debtorRepository.findByPersonalData(optionalPersonalData);
            if(optionalDebtor.isPresent())
                return optionalDebtor.get();
            return null;

        } catch (Exception e) {
            log.debug("Debitore non trovato");
            return null;
        }
    }

}
