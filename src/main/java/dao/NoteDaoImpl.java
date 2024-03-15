package dao;

import model.Note;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NoteDaoImpl implements NoteDao {
    public static final Logger logger = LoggerFactory.getLogger(NoteDaoImpl.class);
    private final Scanner scanner = new Scanner(System.in);
    private final List<Note> allNotes = new ArrayList<>();

    public NoteDaoImpl() {
    }

    @Override
    public void help() {
        logger.info("доступные команды:");
        Commands.printCommands();   //вспомогательный метод. надо логировать?
    }
    @Override
    public void noteNew() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Введите заметку:");
        String content = scanner.nextLine();

        while (content.length() < 3 || content.trim().isEmpty()) {
            logger.info("Заметка не может быть пустой и должна содержать не менее 3 символов. Пожалуйста, введите заметку: Введено: {}", content);
            content = scanner.nextLine();
        }
        logger.info("Добавить метки? Метки состоят из одного слова и могут содержать только буквы.");
        String labelsInput = scanner.nextLine();

        while (isValidLabels(labelsInput)) {
            logger.info("Некорректный формат меток. Метки должны состоять из слов, разделенных пробелами, и содержать только буквы. Введено: {}", labelsInput);
            logger.info("Пожалуйста, введите метки:");
            labelsInput = scanner.nextLine();
        }

        List<String> labels = Arrays.asList(labelsInput.split(" "));

        Note newNote = new Note(content, labels);
        allNotes.add(newNote);
        logger.info("Заметка добавлена");
    }

    private boolean isValidLabels(String labelsInput) {
        Pattern pattern = Pattern.compile("^[A-Za-z\\s]+$");
        Matcher matcher = pattern.matcher(labelsInput);
        return !matcher.matches();
    }


    @Override
    public void noteList() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок:");
        String labelsInput = scanner.nextLine();

        List<Note> filteredNotes;
        if (labelsInput.trim().isEmpty()) {
            filteredNotes = allNotes;
        } else {
            while (isValidLabels(labelsInput)) {
                logger.info("Метки должны быть разделены пробелом и содержать более 3 символов, введено: " + labelsInput);
                logger.info("Пожалуйста, введите метки:");
                labelsInput = scanner.nextLine();
            }
            List<String> labels = Arrays.asList(labelsInput.split(" "));
            filteredNotes = filterNotesByLabels(labels);
        }

        for (Note note : filteredNotes) {
            logger.info("{" + note.getId() + "}#" + note.getContent() + " " + note.getLabels());
        }
    }

    @Override
    public void noteRemove() {
        logger.info("Введите id удаляемой заметки:");
        if (!scanner.hasNextInt()) { // проверка, что введено число
            logger.info("Ошибка, введено не число: " + scanner.nextLine());
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean removed = false;  // Поиск заметки в списке
        Iterator<Note> iterator = allNotes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getId() == id) {
                iterator.remove();
                removed = true;
                logger.info("Заметка удалена успешно");
                break;
            }
        }

        if (!removed) {
            logger.info("Заметка с указанным id не найдена");
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
            logger.info("Заметки успешно сохранены в файл: {}", fileName);
                  } catch (IOException e) {
            logger.warn("Ошибка при сохранении заметок в файл: {}", e.getMessage());
        }
    }
    @Override
    public void noteExit() {// Завершение работы приложения
        logger.info("Работа приложения завершена.");
        System.exit(0);
    }
}




















