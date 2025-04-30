package it.internetIdee.KnockCollector.data.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "installment")
@Data
public class Installment {
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_installment")
    private UUID installmentId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(name = "accrual_date")
    private LocalDate accrualDate;

    @ManyToOne
    @JoinColumn(name = "fk_practice_id", referencedColumnName = "id_practice")
    @JsonIgnore
    private Practice practice;
}
