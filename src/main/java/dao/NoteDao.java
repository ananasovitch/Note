package dao;


import model.Note;

import java.util.List;
import java.util.Scanner;

public interface NoteDao {
    void help();
    void noteNew();
    void noteList();
    void noteRemove();
    void noteExport();
    void noteExit();

}