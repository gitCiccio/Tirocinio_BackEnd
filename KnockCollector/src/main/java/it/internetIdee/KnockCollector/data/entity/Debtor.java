package it.internetIdee.KnockCollector.data.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "debtor")
@Data

public class Debtor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_debtor")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "fk_id_personal_data", referencedColumnName = "id_personal_data")
    private PersonalData personalData;

    @OneToMany(mappedBy = "debtor")
    @JsonManagedReference
    private List<Address> addresses;

}
