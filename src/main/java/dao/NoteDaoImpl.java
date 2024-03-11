package dao;

import model.Note;
import service.NoteService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.notes;


public class NoteDaoImpl implements NoteDao {

    private List<Note> notes; // Список всех заметок

    public NoteDaoImpl(List<Note> notes) {
        this.notes = notes;
    }

    private NoteService noteService;

    public NoteDaoImpl() {
        this.noteService = noteService;
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

        if (content.trim().isEmpty() || content.length() < 3) {
            System.out.println("Слишком короткая заметка. Длина должна быть не менее 3 символов");
            return;
        }

        System.out.println("Добавить метки? ");
        String labelsInput = scanner.nextLine();

        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        Matcher matcher = pattern.matcher(labelsInput);
        if (!matcher.matches()) {
            System.out.println("Некорректные метки. Метки должны состоять из букв и разделяться пробелом.");
            return;
        }

        Note note = new Note(Note.generateId(), "Заголовок", content, Arrays.asList(labelsInput.split(" ")), LocalDateTime.now());
        notes.add(note);

        System.out.println("Заметка добавлена");
    }


    @Override
    public List<Note> noteList() {
        List<Note> matchingNotes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок");
        String labelsInput = scanner.nextLine();

        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        Matcher matcher = pattern.matcher(labelsInput);
        if (!matcher.matches()) {
            System.out.println("Неверные метки. Метки должны состоять из букв и разделяться пробелами.");
            return matchingNotes;
        }

        if (labelsInput.trim().isEmpty()) {
            return notes;
        } else {
            for (Note note : notes) {
                List<String> noteLabels = note.getLabels();
                boolean containsAllLabels = true;
                for (String label : labelsInput.split(" ")) {
                    if (!noteLabels.contains(label)) {
                        containsAllLabels = false;
                        break;
                    }
                }
                if (containsAllLabels) {
                    matchingNotes.add(note);
                }
            }
        }
        return matchingNotes;
    }

    @Override
    public void noteRemove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите метку заметки, которую нужно удалить:");
        String labelToRemove = scanner.nextLine();

        boolean removed = false;
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getLabels().contains(labelToRemove)) {
                iterator.remove();
                System.out.println("Заметка с меткой " + labelToRemove + " удалена.");
                removed = true;
            }
        }
        if (!removed) {
            System.out.println("Заметок с меткой " + labelToRemove + "не найдено.");
        }
    }

    @Override
    public void noteExport() {
        String fileName = "notes_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss")) + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : notes) {
                writer.write("ID: " + note.getId() + "\n");
                writer.write("Title: " + note.getTitle() + "\n");
                writer.write("Content: " + note.getContent() + "\n");
                writer.write("Labels: " + String.join(", ", note.getLabels()) + "\n");
                writer.write("Created: " + note.getCreated() + "\n");
                writer.write("\n");
            }
            System.out.println("Заметка экспортирована в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при экспорте заметок в файл: " + e.getMessage());
        }
    }


    @Override
    public void processUserCommand() {

    }

    @Override
    public void noteExit() {
        System.out.println("Завершение работы приложения");
        System.exit(0);
    }

    @Override
    public void addTagsToNote(int noteId, List<String> tags) {

}}









