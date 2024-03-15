package service;

import dao.Commands;
import dao.NoteDao;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NoteServiceImpl implements NoteService {
    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
    private final NoteDao noteDao;
    private boolean isFirstRun = true;

    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void help() {
        logger.debug("вызвана команда help");  //логировал с помощью SLF4J, уровень `fine` и `DEBUG` дают одинаковый уровень детализации.
        noteDao.help();
    }

    @Override
    public void noteNew() {
        logger.debug("вызвана команда note-new");
        noteDao.noteNew();
    }

    @Override
    public void noteList() {
        logger.debug("вызвана команда note-list");
        noteDao.noteList();
    }

    @Override
    public void noteRemove() {
        logger.debug("вызвана команда note-remove");
        noteDao.noteRemove();
    }

    @Override
    public void noteExport() {
        logger.debug("вызвана команда note-export");
        noteDao.noteExport();
    }

    @Override
    public void noteExit() {
        logger.debug("вызвана команда note-export");
        noteDao.noteExit();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            if (isFirstRun) {
                logger.info("Это Ваша записная книжка. Вот список доступных команд: ");
                Commands.printCommands();
                isFirstRun = false;
            }
            String command = scanner.nextLine();
            switch (command) {
                    case "help":
                        help();
                        break;
                    case "note-new":
                        noteNew();
                        break;
                    case "note-list":
                        noteList();
                        break;
                    case "note-remove":
                        noteRemove();
                        break;
                    case "note-export":
                        noteExport();
                        break;
                    case "note-exit":
                        noteExit();
                        running = false;
                        break;
                    default:
                        logger.info("Команда не найдена");
                }
            }
            scanner.close();
        }
    }