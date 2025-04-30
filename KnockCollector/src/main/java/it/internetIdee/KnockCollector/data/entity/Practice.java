package it.internetIdee.KnockCollector.data.entity;

import java.util.List;
import java.util.UUID;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "practice")
@Data

public class Practice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_practice")
    private UUID practice_id;

    @ManyToOne
    @JoinColumn(name = "debtor", referencedColumnName = "id_debtor")
    private Debtor debtor;

    @Column(name = "credit_to_recover")
    private double creditToRecover;

    @OneToMany
    private List<Installment> installment;

    @OneToMany
    private List<PromiseOfPayment> promiseOfPayment;

    @OneToMany
    private List<Recovery> recovery;

    @OneToMany(mappedBy = "practice")
    private List<Note> notes;
}
