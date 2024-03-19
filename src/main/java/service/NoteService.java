package service;

import model.Note;

import java.util.List;
import java.util.Scanner;

public interface NoteService {
    void help();
    void noteNew();
    boolean noteList();
    void noteRemove();
    void noteExport();
    void noteExit();
    void start();
}