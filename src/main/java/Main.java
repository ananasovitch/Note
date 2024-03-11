import dao.NoteDao;
import dao.NoteDaoImpl;
import service.NoteService;
import service.NoteServiceImpl;

public class Main {
   public static void main(String[] args) {
      NoteDao noteDao = new NoteDaoImpl(); // Создание объекта класса, реализующего интерфейс NoteDao
      NoteService noteService = new NoteServiceImpl(noteDao); // Создание объекта класса, реализующего интерфейс NoteService
      noteService.start(); // Вызов метода start()
   }
}

