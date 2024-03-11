package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Note {
    private static int lastId = 0;
    private int id;
    private String title;
    private String content;
    private List<String> labels;
    private LocalDateTime created;

    public Note(int id, String title, String content, List<String> labels, LocalDateTime created) {
        this.id = ++lastId;
        this.title = title;
        this.content = content;
        this.labels = labels;
        this.created = created;
    }




    // Генератор уникального ID для заметок
    public static int generateId() {
        return ++lastId;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    // Переопределение методов equals и hashCode для сравнения заметок по ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return getId() == note.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}