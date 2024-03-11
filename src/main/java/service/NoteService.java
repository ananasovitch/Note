package service;

import model.Note;

import java.util.List;

public interface NoteService {
    void help();


    void noteNew();


    List<Note> noteList();


    void noteRemove();


    void noteExport();
    void noteExit();
    void start();
}
