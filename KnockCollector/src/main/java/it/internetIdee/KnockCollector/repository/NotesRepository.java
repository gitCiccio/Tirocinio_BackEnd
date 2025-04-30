package it.internetIdee.KnockCollector.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.internetIdee.KnockCollector.data.entity.Note;
import it.internetIdee.KnockCollector.data.entity.Practice;

public interface NotesRepository extends JpaRepository<Note,UUID>{
    
    public List<Note> findByPractice(Practice practice);
}
