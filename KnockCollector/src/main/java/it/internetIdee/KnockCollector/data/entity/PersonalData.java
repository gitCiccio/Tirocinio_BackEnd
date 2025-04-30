package it.internetIdee.KnockCollector.data.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "personal_data")
@Data
public class PersonalData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_personal_data")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "tax_code",unique = true)
    private String taxCode;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "vat", nullable = true)
    private String vat;
}
