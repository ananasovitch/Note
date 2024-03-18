package dao;

import model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import static model.Note.allNotes;
import static org.junit.jupiter.api.Assertions.*;

public class NoteDaoImplTest {
    private NoteDao noteDao;

    @BeforeEach
    public void setup() {
        noteDao = new NoteDaoImpl();
    }

    @Test
    @DisplayName("Добавление новой заметки с корректным вводом")
    public void testNoteNewWithValidInput() {
        String input = "Содержание заметки\nметка1 метка2";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        noteDao.noteNew();
        assertEquals(1, Note.getAllNotes().size());
    }

    @Test
    @DisplayName("Проверка метода isValidLabels на корректных метках")
    public void testIsValidLabelsWithValidLabels() {
        NoteDaoImpl noteDao = new NoteDaoImpl();
        assertTrue(noteDao.isValidLabels("метка1 метка2"));
    }

    @Test
    @DisplayName("Проверка метода isValidLabels на некорректных метках")
    public void testIsValidLabelsWithInvalidLabels() {
        NoteDaoImpl noteDao = new NoteDaoImpl();
        assertFalse(noteDao.isValidLabels("метка1 метка2!"));
    }

    @Test
    @DisplayName("метод `noteList` отображает список заметок корректно")
    public void testNoteList() {
        NoteDaoImpl noteDao = new NoteDaoImpl();
        Note note1 = new Note("Note 1", Arrays.asList("label1", "label2"));
        Note note2 = new Note("Note 2", Arrays.asList("label1"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ByteArrayInputStream in = new ByteArrayInputStream("label1\n".getBytes());
        System.setIn(in);
        noteDao.noteList();
        String expectedOutput = "{" + note1.getId() + "}#" + note1.getContent() + " " + note1.getLabels() + "\n" +
                "{" + note2.getId() + "}#" + note2.getContent() + " " + note2.getLabels() + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Тестирование метода удаления заметки")
    void testNoteRemove() {
        Note note1 = new Note("Note 1", Arrays.asList("label1", "label2"));
        Note note2 = new Note("Note 2", Arrays.asList("label1"));
        Note note3 = new Note("Note 3", Arrays.asList("label2"));
        allNotes.add(note1);
        allNotes.add(note2);
        allNotes.add(note3);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ByteArrayInputStream in = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(in);
        noteDao.noteRemove();
        String expectedOutput = "Введите id удаляемой заметки:\nЗаметка удалена успешно\n";
        assertEquals(expectedOutput, outContent.toString());
        assertFalse(allNotes.contains(note2));
    }

    @Test
    @DisplayName("Тестирование метода экспорта заметок")
    void testNoteExport() {
        Note note1 = new Note("Содержание заметки 1", Arrays.asList("метка1", "метка2"));
        Note note2 = new Note("Содержание заметки 2", Arrays.asList("метка1"));
        Note.getAllNotes().add(note1);
        Note.getAllNotes().add(note2);
        noteDao.noteExport();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String fileName = "notes_" + sdf.format(new Date()) + ".txt";
        File file = new File(fileName);
        assertTrue(file.exists());
        try (Scanner scanner = new Scanner(file)) {
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            assertEquals("{" + note1.getId() + "}#" + note1.getContent() + " {" + String.join("; ", note1.getLabels()) + "}", line1);
            assertEquals("{" + note2.getId() + "}#" + note2.getContent() + " {" + String.join("; ", note2.getLabels()) + "}", line2);
        } catch (FileNotFoundException e) {
            fail("Файл не найден: " + e.getMessage());
        }
    }
}




