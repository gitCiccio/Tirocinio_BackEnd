package it.internetIdee.KnockCollector.data.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Address;
import it.internetIdee.KnockCollector.data.entity.CityHall;
import it.internetIdee.KnockCollector.repository.AddressRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
    
    private final AddressRepository addressRepository;
    private final CityHallService cityHallService;

    public List<Address> findaAllAddresses() {
        return addressRepository.findAll();
    }

    public Address findAddress(UUID addressId){
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if(optionalAddress.isPresent()){
            return optionalAddress.get();
        }
        return null;
    }

    public Address findAddressByHouseNumber(String houseNumer){
        Optional<Address> addr = addressRepository.findByHouseNumber(houseNumer);
        if(addr.isPresent()){
            return addr.get();
        }
        return null;
    }

    public Address addAddress(Address address){
        CityHall savedCityHall = cityHallService.addCityHall(address.getCityHall());
        address.setCityHall(savedCityHall);

        return addressRepository.save(address);
    }

    
    public List<Address> addAll(List<Address> addresses){
        for(Address address : addresses){
            CityHall savedCityHall = cityHallService.addCityHall(address.getCityHall());
            address.setCityHall(savedCityHall);
        }
        return addressRepository.saveAll(addresses);
    }
}
