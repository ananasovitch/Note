package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Note {
    // Статический список для хранения всех заметок
    private static List<Note> allNotes = new ArrayList<>();
    private static int idCounter = 0;
    public int id;
    public String content;
    public List<String> labels;


    public Note(int i, String content, List<String> labels) {
        this.id = generateId();
        this.content = content;
        this.labels = labels;
        // Добавляем новую заметку в список всех заметок
        allNotes.add(this);
    }
    // Геттеры и сеттеры для полей класса

    public static int generateId() {
        return ++idCounter;
    }

    public boolean hasLabels(List<String> checkLabels) {
        return new HashSet<>(labels).containsAll(checkLabels);
    }

    public int getId() {
        return id;
    }

    public String getLabelsAsString() {
        return String.join(", ", labels);
    }

    public String getContent() {
        return content;
    }

    public List<String> getLabels() {
        return labels;
    }

    // Геттер для списка всех заметок
    public static List<Note> getAllNotes() {

        return allNotes;
    }

    public void setId(int newId) {
        this.id = newId;
    }

      public void setContent(String newContent) {
        this.content = newContent;
    }
    public boolean containsAllLabels(List<String> labels) {
        return new HashSet<>(this.labels).containsAll(labels);
    }
}


