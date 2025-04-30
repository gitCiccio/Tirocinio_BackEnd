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
@Table(name = "agent")
@Data
public class Agent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "agent_id")
    private UUID agentId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
