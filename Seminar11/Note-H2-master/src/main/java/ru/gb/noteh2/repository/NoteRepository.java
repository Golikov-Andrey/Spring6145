package ru.gb.noteh2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.noteh2.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
