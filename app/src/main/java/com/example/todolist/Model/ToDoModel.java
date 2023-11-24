package com.example.todolist.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDoModel {
    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name = "task")
    private String task;

    @ColumnInfo(name = "status")
    private boolean status;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
