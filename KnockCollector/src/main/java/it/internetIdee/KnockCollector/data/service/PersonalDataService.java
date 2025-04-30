package it.internetIdee.KnockCollector.data.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.PersonalData;
import it.internetIdee.KnockCollector.repository.PersonalDataRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalDataService {
    
    private final PersonalDataRepository personalDataRepository;


    public PersonalData findPersonalData(UUID idPersonalData){
        Optional<PersonalData> optionalPersonalData = personalDataRepository.findById(idPersonalData);
        if(optionalPersonalData.isPresent()){
            return optionalPersonalData.get();
        }
        return null;
    }

    public PersonalData addPersonalData(PersonalData personalData){
        System.out.println("Personal data: "+personalData.getName());
        return personalDataRepository.save(personalData);
    }

    public PersonalData getPersonalDataByTaxCode(String taxCode){
        Optional<PersonalData> optionalPersonalData = personalDataRepository.findByTaxCode(taxCode);
        if(optionalPersonalData.isPresent())
            return optionalPersonalData.get();
        return null;
    }
}
