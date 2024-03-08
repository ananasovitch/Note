package dao;


import model.Note;

import java.util.List;

public interface NoteDao {
    void help();

    void noteNew();

    void noteList();

    void noteRemove();

    void noteExport();
    void noteExit();
    void addTagsToNote(int noteId, List<String> tags);

}
