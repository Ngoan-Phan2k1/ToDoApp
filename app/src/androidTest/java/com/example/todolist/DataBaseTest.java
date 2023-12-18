package com.example.todolist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.todolist.Model.ToDoModel;
import com.example.todolist.Utils.DataBaseHelper;
import com.example.todolist.Utils.ToDoDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DataBaseTest {

    private ToDoDAO toDoDAO;
    private DataBaseHelper dataBaseHelper;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        dataBaseHelper = Room.inMemoryDatabaseBuilder(context, DataBaseHelper.class).build();
        toDoDAO = dataBaseHelper.toDoDAO();
    }

    @After
    public void closeDb() throws IOException {
        dataBaseHelper.close();
    }

    @Test
    public void writeTaskAndReadInList() throws Exception {
        ToDoModel taskTest = new ToDoModel();
        taskTest.setTask("Task Test");
        taskTest.setStatus(false);
        taskTest.setId(10);

        toDoDAO.insert(taskTest);
        List<ToDoModel> tasks = toDoDAO.getAllTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    public void writeTaskAndDeleteInList() throws Exception {
        ToDoModel taskTest = new ToDoModel();
        taskTest.setTask("Task Test");
        taskTest.setStatus(false);
        taskTest.setId(10);
        toDoDAO.insert(taskTest);

        toDoDAO.delete(taskTest.getId());
        List<ToDoModel> tasks = toDoDAO.getAllTasks();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void writeTaskAndUpdateTaskInList() throws Exception {
        ToDoModel taskTest = new ToDoModel();
        taskTest.setTask("Task Test");
        taskTest.setStatus(false);
        taskTest.setId(10);
        toDoDAO.insert(taskTest);

        toDoDAO.updateTask(taskTest.getId(), "Task update");
        List<ToDoModel> tasks = toDoDAO.getAllTasks();
        ToDoModel taskCheck = tasks.get(0);
        assertEquals("Task update", taskCheck.getTask());
    }

    @Test
    public void writeTaskAndUpdateStatusInList() throws Exception {

        ToDoModel taskTest = new ToDoModel();
        taskTest.setTask("Task Test");
        taskTest.setStatus(false);
        taskTest.setId(10);
        toDoDAO.insert(taskTest);

        toDoDAO.updateStatus(taskTest.getId(), true);
        List<ToDoModel> tasks = toDoDAO.getAllTasks();
        ToDoModel taskCheck = tasks.get(0);
        assertEquals(true, taskCheck.isStatus());
    }
}
