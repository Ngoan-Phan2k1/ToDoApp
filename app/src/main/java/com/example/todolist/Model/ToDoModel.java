package com.example.todolist.Model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class ToDoModel {
    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name = "task")
    private String task;

    @ColumnInfo(name = "status")
    private boolean status;

    @ColumnInfo(name="creation_time")
    private Long createdAt;

    @ColumnInfo(name="priority", defaultValue = "1")
    private int priority;

    public ToDoModel(int id, String task, boolean status, Long createdAt) {
        this.id = id;
        this.task = task;
        this.status = status;
        this.createdAt = createdAt;
        this.priority = 1;
    }

//    public ToDoModel() {
//
//    }

//    public String getTask() {
//        return task;
//    }
//
//    public void setTask(String task) {
//        this.task = task;
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public Long getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Long createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public int getPriority() {
//        return priority;
//    }
//
//    public void setPriority(int priority) {
//        this.priority = priority;
//    }

}
