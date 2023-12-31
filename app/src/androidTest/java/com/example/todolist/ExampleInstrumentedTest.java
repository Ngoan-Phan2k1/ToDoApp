package com.example.todolist;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.todolist.Model.ToDoModel;
import com.example.todolist.Utils.DataBaseHelper;
import com.example.todolist.Utils.ToDoDAO;

import java.io.IOException;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private ToDoDAO toDoDAO;
    private DataBaseHelper dataBaseHelper;

//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.example.todolist", appContext.getPackageName());
//    }

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
        //assertTrue(tasks.size() > 0);
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