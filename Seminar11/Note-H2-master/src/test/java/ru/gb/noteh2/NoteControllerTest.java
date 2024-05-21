package ru.gb.noteh2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.gb.noteh2.controller.NoteController;
import ru.gb.noteh2.model.Note;
import ru.gb.noteh2.service.NoteService;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class NoteControllerTest {
    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

   private LocalDateTime dateTime;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
        objectMapper = new ObjectMapper();
        dateTime = LocalDateTime.now();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/notes/getAll")).andExpect(status().isOk());
    }

    @Test
    void createNoteTest() throws Exception {

        Note note = new Note(1L, "Заметка", "Тест", dateTime);
        String jsonNote = objectMapper.writeValueAsString(note);
        mockMvc.perform(post("/notes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonNote)
        ).andExpect(status().isCreated());
    }
}
