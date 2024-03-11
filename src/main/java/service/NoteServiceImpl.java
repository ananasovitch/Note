package service;

import dao.Commands;
import dao.NoteDao;
import dao.NoteDaoImpl;
import model.Note;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.notes;

public class NoteServiceImpl implements NoteService {
    private NoteDao noteDao;

    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду:");
            Commands.printCommands();  // Используем метод printCommands из класса Commands для вывода списка доступных команд

            String input = scanner.nextLine();

            try {
                Commands command = Commands.valueOf(input.toUpperCase());
                switch (command) {
                    case HELP:
                        help();
                        break;
                    case NOTE_NEW:
                        noteNew();
                        break;
                    case NOTE_LIST:
                        noteList();
                        break;
                    case NOTE_REMOVE:
                        noteRemove();
                        break;
                    case NOTE_EXPORT:
                        noteExport();
                        break;
                    case EXIT:
                        noteExit();
                        return;
                    default:
                        System.out.println("Неизвестная команда");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неизвестная команда");
            }
        }
    }

    @Override
    public void help() {
        Commands.printCommands();
    }

    @Override
    public void noteNew() {
        noteDao.noteNew();
    }

    @Override
    public List<Note> noteList() {
       return   noteDao.noteList();


    }

    @Override
    public void noteRemove() {
       noteDao.noteRemove(); // Реализация удаления заметки
    }

    @Override
    public void noteExport() {
       noteDao.noteExport(); // Реализация экспорта заметок
    }

    @Override
    public void noteExit() {
        System.out.println("До свидания!");
    }
}