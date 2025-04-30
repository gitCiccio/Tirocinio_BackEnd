package it.internetIdee.KnockCollector.data.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.CityHall;
import it.internetIdee.KnockCollector.repository.CityHallRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityHallService {
    
    private final CityHallRepository cityHallRepository;

    public List<CityHall> findAllCityData(){
        return cityHallRepository.findAll();
    }

    public CityHall findCityData(UUID cityDataId){
        Optional<CityHall> optionalCityHall = cityHallRepository.findById(cityDataId);
        if(optionalCityHall.isPresent()){
            return optionalCityHall.get();
        }
        return null;
    }

    public CityHall addCityHall(CityHall cityHall){
        return cityHallRepository.save(cityHall);
    }

    public void deleCityHall(UUID cityHallId){
        cityHallRepository.deleteById(cityHallId);
    }
}
