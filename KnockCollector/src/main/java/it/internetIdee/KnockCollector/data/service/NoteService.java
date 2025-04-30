package it.internetIdee.KnockCollector.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.internetIdee.KnockCollector.data.entity.Note;
import it.internetIdee.KnockCollector.data.entity.Practice;
import it.internetIdee.KnockCollector.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteService {
    
    private final NotesRepository notesRepository;

    public List<Note> addNote(List<Note> notes, Practice practice){
        List<Note> result = new ArrayList<>();
        try {
            for(Note note : notes){
                note.setPractice(practice);
                result.add(note);
            }
            notesRepository.saveAll(result);
            return result;
        } catch (Exception e) {
            log.debug("Errore nell'aggiunta del recupero");
            return null;
        }
    }

    public Note getNoteById(UUID noteId){
        try {
            Optional<Note> optionalNote = notesRepository.findById(noteId);
            if(optionalNote.isPresent())
                return optionalNote.get();
            return null;
        } catch (Exception e) {
            log.debug("Nota non trovata");
            return null;
        }
    }

    public List<Note> getNotesByPractice(Practice practice){
        try {
            List<Note> practiceNotes = notesRepository.findByPractice(practice);
            if(!practiceNotes.isEmpty())
                return practiceNotes;
            return null;
        } catch (Exception e) {
            log.debug("Impossibile recuperare la lista di note.");
            return null;
        }
    }


}
