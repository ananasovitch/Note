package dao;


import model.Note;

import java.util.List;

public interface NoteDao {
    void help();

    void noteNew();

    List<Note> noteList();

    void noteRemove();

    void noteExport();

    void processUserCommand();

    void noteExit();
    void addTagsToNote(int noteId, List<String> tags);

}