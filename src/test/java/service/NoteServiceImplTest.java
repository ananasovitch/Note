package service;

import dao.NoteDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class NoteServiceImplTest {

    private NoteDao noteDao;
    private NoteServiceImpl noteService;

    @BeforeEach
    void setUp() {
        noteDao = mock(NoteDao.class);
        noteService = new NoteServiceImpl(noteDao);
    }

    @Test
    @DisplayName("Тест для метода help")
    public void testHelp() {
        noteService.help();
        verify(noteDao).help();
    }

    @Test
    @DisplayName("Тест для метода noteNew")
    public void testNoteNew() {
        noteService.noteNew();
        verify(noteDao).noteNew();
    }

    @Test
    @DisplayName("Тест для метода noteList")
    public void testNoteList() {
        when(noteService.noteList()).thenReturn(true);
        boolean result = noteService.noteList();
        Assertions.assertTrue(result);
        verify(noteDao).noteList();
    }

    @Test
    @DisplayName("Тест для метода noteRemove")
    public void testNoteRemove() {
        noteService.noteRemove();
        verify(noteDao).noteRemove();
    }

    @Test
    @DisplayName("Тест для метода noteExport")
    public void testNoteExport() {
        noteService.noteExport();
        verify(noteDao).noteExport();
    }

    @Test
    @DisplayName("Тест для метода noteExit")
    public void testNoteExit() {
        noteService.noteExit();
        verify(noteDao).noteExit();
    }
}
