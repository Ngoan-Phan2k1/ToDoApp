package com.example.todolist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;

import android.content.Context;
import android.util.Log;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    private MainActivity mainActivity;
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSortByAlphabet() {

        List<ToDoModel> mockList = new ArrayList<>();

        ToDoModel task1 = new ToDoModel(1, "Task A", false, System.currentTimeMillis());
        ToDoModel task2 = new ToDoModel(2, "Task C", false, System.currentTimeMillis());
        ToDoModel task3 = new ToDoModel(3, "Task B", false, System.currentTimeMillis());
        mockList.add(task1);
        mockList.add(task2);
        mockList.add(task3);

        Mockito.when(mainActivity.sortByAlphabet(mockList)).thenCallRealMethod();
        List<ToDoModel> sortedList = mainActivity.sortByAlphabet(mockList);

        assertEquals(task1, sortedList.get(0));
        assertEquals(task3, sortedList.get(1));
        assertEquals(task2, sortedList.get(2));
    }

    @Test
    public void testSortByTime() {

        List<ToDoModel> mockList = new ArrayList<>();
        ToDoModel task1 = new ToDoModel(1, "Task A", false, System.currentTimeMillis() + 1);
        ToDoModel task2 = new ToDoModel(2, "Task C", false, System.currentTimeMillis() + 2);
        ToDoModel task3 = new ToDoModel(3, "Task B", false, System.currentTimeMillis() + 3);

        mockList.add(task1);
        mockList.add(task3);
        mockList.add(task2);

        Mockito.when(mainActivity.sortByTime(mockList)).thenCallRealMethod();
        List<ToDoModel> sortedList = mainActivity.sortByTime(mockList);

        assertEquals(task1, sortedList.get(0));
        assertEquals(task2, sortedList.get(1));
        assertEquals(task3, sortedList.get(2));
    }



}