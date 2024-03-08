package model;


import java.util.*;

public class Note {
    private static int idCounter = 0;
    private int id;
    private String title;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note note)) return false;
        return Objects.equals(getTags(), note.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTags());
    }

    private List<String> tags;
    

    private static List<Note> allNotes = new ArrayList<>();

    public Note() {
        this.id = generateId();
    }

    public Note(String title, String content) {
        this.id = generateId();
        this.title = title;
        this.content = content;
        allNotes.add(this);
    }

    public <T> Note(int newId, String noteContent, List<T> list) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static List<Note> getAllNotes() {
        return allNotes;
    }

    private int generateId() {
        return ++idCounter;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public List<String> getTags() {
        return tags;
}

}