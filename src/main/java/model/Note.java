package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Note {
    private static final List<Note> allNotes = new ArrayList<>(); // Статический список для хранения всех заметок
    private static int idCounter = 0;
    public int id;
    public String content;
    public List<String> labels;


    public Note(String content, List<String> labels) {
        this.id = generateId();
        this.content = content;
        this.labels = labels;
        allNotes.add(this);// Добавил новую заметку в список всех заметок
    }

    public static int generateId() {
        return ++idCounter;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public List<String> getLabels() {
        return labels;
    }

    public static List<Note> getAllNotes() {
        return allNotes;
    }

    public boolean containsAllLabels(List<String> labels) {
        return new HashSet<>(this.labels).containsAll(labels);
    }
}


