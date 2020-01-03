package com.example.mvvmarch;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao ;
    private LiveData<List<Note>>  allNotes ;

    public NoteRepository(Application application){
        NoteDatabases databases = NoteDatabases.getInstance(application);
        noteDao = databases.noteDao();
        allNotes = noteDao.getAllNotes() ;

    }



    public void insert (Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);

    }
    public void update(Note note){
        new UpdatetNoteAsyncTask(noteDao).execute(note);


    }
    public void delete (Note note){
        new DeletetNoteAsyncTask(noteDao).execute(note);


    }
    public void deleteAllNotes (){
        new DeletetAllNotesAsyncTask(noteDao).execute();

    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }





    private static  class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao ;

        private  InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao ;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static  class UpdatetNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao ;

        private  UpdatetNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao ;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static  class DeletetNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao ;

        private  DeletetNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao ;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static  class DeletetAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao ;

        private  DeletetAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao ;
        }



        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }




}
