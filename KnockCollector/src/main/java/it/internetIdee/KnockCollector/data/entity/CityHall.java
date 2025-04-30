package it.internetIdee.KnockCollector.data.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "city_hall")
@Data
public class CityHall {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_city_hall")
    private UUID id;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "province")
    private String province;

    @Column(name = "region")
    private String region;

    @Column(name = "zip_code")
    private String zip_code;
}
