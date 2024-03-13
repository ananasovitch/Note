package dao;

import model.Note;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NoteDaoImpl implements NoteDao {
    private  List<Note> allNotes = new ArrayList<>();
    public NoteDaoImpl() {
    }
    @Override
    public void help() {
        System.out.println("Available commands:");
        Commands.printCommands();
    }


    @Override
    public void noteNew() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите заметку:");
        String content = scanner.nextLine();

        while (content.length() < 3 || content.trim().isEmpty()) {
            System.out.println("Заметка не может быть пустой и должна содержать не менее 3 символов. Пожалуйста, введите заметку:");
            content = scanner.nextLine();
        }

        System.out.println("Добавить метки? Метки состоят из одного слова и могут содержать только буквы.");
        String labelsInput = scanner.nextLine();

        while (!isValidLabels(labelsInput)) {
            System.out.println("Некорректный формат меток. Метки должны состоять из слов, разделенных пробелами, и содержать только буквы.");
            System.out.println("Пожалуйста, введите метки:");
            labelsInput = scanner.nextLine();
        }

        List<String> labels = Arrays.asList(labelsInput.split(" "));

        int newId = Note.generateId();
        Note newNote = new Note(newId, content, labels);
        allNotes.add(newNote);

        System.out.println("Заметка добавлена");
    }

    private boolean isValidLabels(String labelsInput) {
        Pattern pattern = Pattern.compile("^[A-Za-z\\s]+$");
        Matcher matcher = pattern.matcher(labelsInput);
        return matcher.matches();
    }




    @Override
    public void noteList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок:");
        String labelsInput = scanner.nextLine();

        List<Note> filteredNotes;
        if(labelsInput.trim().isEmpty()) {
            filteredNotes = allNotes;
        } else {
            while (!isValidLabels(labelsInput)) {
                System.out.println("Некорректный формат меток. Метки должны состоять из слов, разделенных пробелами, и содержать только буквы.");
                System.out.println("Пожалуйста, введите метки:");
                labelsInput = scanner.nextLine();
            }
            List<String> labels = Arrays.asList(labelsInput.split(" "));
            filteredNotes = filterNotesByLabels(labels);
        }

        for (Note note : filteredNotes) {
            System.out.print("{" + note.getId() + "}#" + note.getContent() + " " + note.getLabels() + "\n");
        }
    }

    private List<Note> filterNotesByLabels(List<String> labels) {
        List<Note> filteredNotes = new ArrayList<>();
        for (Note note : allNotes) {
            if (note.containsAllLabels(labels)) {
                filteredNotes.add(note);
            }
        }
        return filteredNotes;
    }

    @Override
    public void noteRemove() {

    }

    @Override
    public void noteExport() {

    }

    @Override
    public void noteExit() {

    }
}





















