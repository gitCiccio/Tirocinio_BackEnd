package it.internetIdee.KnockCollector.data.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_address")
    private UUID id;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "name_street")
    private String nameStreet;

    @Column(name = "type_street")
    private String typeStreet;

    @OneToOne
    @JoinColumn(name = "fk_id_city_hall", referencedColumnName = "id_city_hall")
    private CityHall cityHall;

    @ManyToOne
    @JoinColumn(name = "id_debtor", referencedColumnName = "id_debtor")
    @JsonBackReference
    private Debtor debtor;
}
