package dao;

import model.Note;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class NoteDaoImpl implements NoteDao {
    private List<Note> noteList = new ArrayList<>();


    @Override
    public void help() {
        System.out.println("Available commands:");
        Commands.printCommands();

    }

    @Override
    public void noteNew() {
        Scanner scanner = new Scanner(System.in);

// Введите заметку
        System.out.println("Enter note:");
        String noteContent = scanner.nextLine();

// Проверка на пустой ввод или менее 3 символов
        if (noteContent.trim().isEmpty() || noteContent.length() < 3) {
            System.out.println("Note should not be empty and length should be at least 3 characters.");
            return;
        }

    }

    @Override
    public void noteList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок:");
        String input = scanner.nextLine();
        List<String> searchTags = Arrays.asList(input.split(";")); // Разделяем введенные метки на отдельные элементы по разделителю ";"

        // Выводим на экран подходящий список заметок в требуемом формате
        for (Note note : noteList) {
            if (searchTags.isEmpty() || note.getTags().containsAll(searchTags)) {
                System.out.print("{" + note.getId() + "}" + "#" + note.getContent() + " ");
                for (String tag : note.getTags()) {
                    System.out.print(tag + ";");
                }
                System.out.println();
            }
        }
    }

    @Override
    public void noteRemove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID удаляемой заметки:");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            Optional<Note> matchingNote = noteList.stream().filter(note -> note.getId() == id).findFirst();
            if (matchingNote.isPresent()) {
                noteList.remove(matchingNote.get());
                System.out.println("Заметка с ID " + id + " удалена");
            } else {
                System.out.println("Заметка с ID " + id + " не найдена");
            }
        } else {
            System.out.println("Ошибка: введенный ID не является числом");
        }

    }

    @Override
    public void noteExport() {
        String fileName = "notes_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss")) + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : noteList) {
                writer.write("{" + note.getId() + "}#" + note.getContent() + " ");
                for (String tag : note.getTags()) {
                    writer.write(tag + ";");
                }
                writer.newLine();
            }
            System.out.println("Заметки успешно экспортированы в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при экспорте заметок: " + e.getMessage());
        }
    }

    @Override
    public void noteExit() {
        System.out.println("Завершение работы приложения");
        System.exit(0);
    }

    @Override
    public void addTagsToNote(int noteId, List<String> tags) {
        for (Note note : noteList) {
            if (note.getId() == noteId) {
                note.getTags().addAll(tags);
                System.out.println("Tags added to note with ID " + noteId);
                return;
            }
        }
        System.out.println("Note with ID " + noteId + " not found");
    }

}