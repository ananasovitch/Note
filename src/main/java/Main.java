import dao.NoteDao;
import dao.NoteDaoImpl;
import service.NoteService;
import service.NoteServiceImpl;

public class Main {
    public static void main(String[] args) {
        NoteDao noteDao = new NoteDaoImpl();
        NoteService noteService = new NoteServiceImpl(noteDao);
        noteService.start();
    }
}



