package com.example.hw6.service;

import com.example.hw6.domain.Note;
import com.example.hw6.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    /**
     * Метод получения всех заметок
     * @return возвращаем список заметок
     */

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    /**
     * Метод создания новой заметки
     * @param note объект заметки
     * @return возвращаем объект заметки
     */

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Метод поиска заметки по id
     * @param id id нужной нам заметки
     * @return возвращаем найденную заметку, или постой объект, если заметка не найдена
     */

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    /**
     * Метод изменяет заметку
     * @param noteDetails объект заметки
     * @return возвращаем измененную заметку
     */

    public Note updateNote(Long id, Note noteDetails) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(noteDetails.getTitle());
            note.setDescription(noteDetails.getDescription());
            return noteRepository.save(note);
        } else {
            throw new IllegalArgumentException("Note not found with id: " + id);
        }
    }

    /**
     * Метод удаляет заметку по id
     * @param id id заметки
     */

    public void deleteProduct(Long id) {
        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }

}
