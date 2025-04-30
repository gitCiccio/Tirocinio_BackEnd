package it.internetIdee.KnockCollector.data.service;



import it.internetIdee.KnockCollector.data.entity.Agent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AgentService agentService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Agent agent = agentService.getAgentByEmail(email);
        if (agent == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }

        return new User(
            agent.getEmail(),
            agent.getPassword(),
            Collections.singleton(() -> "ROLE_" + agent.getRole().toUpperCase()) 
        );
    }
}

