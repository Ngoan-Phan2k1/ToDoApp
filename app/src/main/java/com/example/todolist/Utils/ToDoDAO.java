package com.example.todolist.Utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todolist.Model.ToDoModel;

import java.util.List;

@Dao
public interface ToDoDAO {

    @Query("SELECT * FROM todomodel")
    List<ToDoModel> getAllTasks();

    @Insert
    void insert(ToDoModel toDoModel);

    @Query("DELETE FROM todomodel WHERE id = :id")
    void delete(int id);

    @Query("UPDATE todomodel SET status = :status WHERE id = :id")
    void updateStatus(int id, boolean status);

    @Query("UPDATE todomodel SET task = :task WHERE id = :id")
    void updateTask(int id, String task);
}
