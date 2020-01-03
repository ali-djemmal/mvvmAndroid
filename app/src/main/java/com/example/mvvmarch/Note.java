package com.example.mvvmarch;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "note_table")

public class Note {
    private int id ;
    private String title ;
    private String description ;

    private int priority ;


    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
