package model;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class NoteTest extends TestCase {

    public void testNoteCreation() {
        Note note = new Note("Test content", Arrays.asList("label1", "label2"));
        assertTrue(Note.getAllNotes().contains(note));
        assertEquals("Test content", note.getContent());
        assertEquals(Arrays.asList("label1", "label2"), note.getLabels());
    }


    public void testContainsAllLabels() {
        Note note = new Note("Test content", Arrays.asList("label1", "label2"));
        assertTrue(note.containsAllLabels(Arrays.asList("label1", "label2")));
        assertFalse(note.containsAllLabels(Arrays.asList("label1", "label3")));
    }

    public void testIdGeneration() {
        Note note1 = new Note("Test content 1", List.of("label1"));
        Note note2 = new Note("Test content 2", List.of("label2"));
        assertNotEquals(note1.getId(), note2.getId());
    }
}