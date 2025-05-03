package it.internetIdee.KnockCollector.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "otp")
@Data
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "otp_id")
    private UUID id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "otp")
    private String otp;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "used")
    private boolean used;

}
