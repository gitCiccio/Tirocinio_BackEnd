package it.internetIdee.KnockCollector.data.entity;

import java.time.LocalDateTime;
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
@Table(name = "notes")
@Data
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_note")
    private UUID noteId;

    @Column(name = "text")
    private String text;

    @Column(name = "date_note")
    private LocalDateTime dateNote;

    @ManyToOne
    @JoinColumn(name = "fk_id_practice", referencedColumnName = "id_practice")
    @JsonIgnore
    private Practice practice;
}
