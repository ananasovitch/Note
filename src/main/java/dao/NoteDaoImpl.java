package dao;

import model.Note;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NoteDaoImpl implements NoteDao {
    private Scanner scanner = new Scanner(System.in);
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

    @Override
    public void noteRemove() {

        System.out.println("Введите id удаляемой заметки:");
        if (!scanner.hasNextInt()) { // проверка, что введено число
            System.out.println("Ошибка: введено не число");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine(); // съедаем оставшийся перевод строки

        // Поиск заметки в списке
        boolean removed = false;
        Iterator<Note> iterator = allNotes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getId() == id) {
                iterator.remove();
                removed = true;
                System.out.println("Заметка удалена успешно");
                break;
            }
        }

        if (!removed) {
            System.out.println("Заметка с указанным id не найдена");
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
    public void noteExport() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String fileName = "notes_" + sdf.format(new Date()) + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : Note.getAllNotes()) {
                String line = "{" + note.getId() + "}#" + note.getContent() + " {" + String.join("; ", note.getLabels()) + "}";
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Заметки успешно сохранены в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении заметок в файл: " + e.getMessage());
        }
    }





    @Override
    public void noteExit() {
// Завершение работы приложения
        System.out.println("Работа приложения завершена.");
        System.exit(0);
    }
}





















