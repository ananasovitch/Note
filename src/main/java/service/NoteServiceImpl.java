package service;

import dao.Commands;
import dao.NoteDao;

import java.util.Scanner;
import java.util.logging.Logger;

public class NoteServiceImpl implements NoteService {
    private NoteDao noteDao;
    private Scanner scanner;

    public NoteServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void help() {

    }

    @Override
    public void newNote() {
        noteDao.noteNew();
    }

    @Override
    public void listNotes() {
        noteDao.noteList();
    }

    @Override
    public void removeNote() {
        noteDao.noteRemove();
    }

    @Override
    public void exportNotes() {
        noteDao.noteExport();
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.println("Enter a command (help, new, list, remove, export, exit):");
            command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "help":
                    Commands.printCommands(); // Отображение списка команд
                    break;
                case "new":
                    newNote();
                    break;
                case "list":
                    listNotes();
                    break;
                case "remove":
                    removeNote();
                    break;
                case "export":
                    exportNotes();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Unknown command");
            }
        } while (!command.equals("exit"));
    }


}
