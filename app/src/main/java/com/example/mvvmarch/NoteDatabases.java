package com.example.mvvmarch;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database( entities = {Note.class},version = 1)

public abstract class NoteDatabases extends RoomDatabase {
    private static NoteDatabases instance ;
    public abstract NoteDao noteDao();
    public static synchronized NoteDatabases getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabases.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roCallback)
                    .build();
        }
        return instance ;
    }

    private static RoomDatabase.Callback roCallback
            = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    } ;

    private static class PopulateDbAsncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao ;
        private PopulateDbAsncTask(NoteDatabases db){
            noteDao = db.noteDao() ;

        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("T1","Desc1",1));
            noteDao.insert(new Note("T2","Desc2",2));
            noteDao.insert(new Note("T3","Desc3",3));
            noteDao.insert(new Note("T4","Desc4",4));
            return null;
        }
    }

}
