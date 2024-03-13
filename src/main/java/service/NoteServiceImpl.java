package service;

import dao.NoteDao;
import model.Note;

import java.util.List;
import java.util.Scanner;



public class NoteServiceImpl implements NoteService {
    private final NoteDao noteDao;

    public NoteServiceImpl(NoteDao noteDao) {

        this.noteDao = noteDao;
    }

    @Override
    public void help() {
        noteDao.help();
    }

    @Override
    public void noteNew() {

        noteDao.noteNew();
    }

    @Override
    public void noteList() {    // public List<Note> noteList() {
        noteDao.noteList();
    }


    @Override
    public void noteRemove() {
        noteDao.noteRemove();
    }

    @Override
    public void noteExport() {
        noteDao.noteExport();
    }

    @Override
    public void noteExit() {
        noteDao.noteExit();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Введите команду: ");
            String command = scanner.nextLine();
            switch (command) {
                case "help":
                    help();
                    break;
                case "noteNew":
                    noteNew();
                    break;
                case "noteList":
                    noteList();
                    break;

                    case "noteRemove":
                    noteRemove();
                    break;
                case "noteExport":
                    noteExport();
                    break;
                case "noteExit":
                    noteExit();
                    running = false;
                    break;
                default:
                    System.out.println("Неверная команда");
            }
        }
        scanner.close();
    }
}