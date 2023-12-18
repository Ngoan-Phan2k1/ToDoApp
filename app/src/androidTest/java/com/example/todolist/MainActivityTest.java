package com.example.todolist;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.Model.ToDoModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private static final String stringToBeTyped = "Task For Test";
    private static final String stringToBeUpdated = "Task Updated";

    @Test
    public void testLayoutVisible() {

        List<ToDoModel> testData = new ArrayList<>();

        testData.add(new ToDoModel(1, "Task 1", false, System.currentTimeMillis()));
        testData.add(new ToDoModel(2, "Task 2", true, System.currentTimeMillis()));

        activityRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recyclerview);
            ToDoAdapter adapter = (ToDoAdapter) recyclerView.getAdapter();
            adapter.setTasks(testData);
        });

        onView(withId(R.id.textview)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testButtonClick() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.edittext)).check(matches(isDisplayed()));
    }

    @Test
    public void testSaveButtonClick() {

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.edittext))
                .perform(typeText(stringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.button_save)).perform(click());
        onView(withId(R.id.recyclerview))
                .check(matches(hasDescendant(withText(stringToBeTyped))));
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));

    }

    @Test
    public void testSwipeRightToUpdateTask() {

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.edittext))
                .perform(typeText(stringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.button_save)).perform(click());
        onView(withId(R.id.recyclerview)).perform(swipeLeft());
        onView(withId(R.id.edittext)).check(matches(isDisplayed()));
        onView(withId(R.id.button_save)).check(matches(isDisplayed()));
        onView(withId(R.id.edittext)).check(matches(withText(stringToBeTyped)));
        onView(withId(R.id.edittext))
                .perform(replaceText(stringToBeUpdated), closeSoftKeyboard());

        onView(withId(R.id.button_save)).perform(click());
        onView(withId(R.id.recyclerview)).check(matches(hasDescendant(withText(stringToBeUpdated))));
        onView(withText(stringToBeUpdated)).check(matches(withText(stringToBeUpdated)));
    }

    @Test
    public void testSwipeLeftDeleteTask() {

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.edittext))
                .perform(typeText(stringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.button_save)).perform(click());
        onView(withId(R.id.recyclerview)).perform(swipeRight());

        onView(withText("Delete Task")).check(matches(isDisplayed()));
        onView(withText("Yes")).perform(click());
        onView(withId(R.id.recyclerview)).check(matches(not(hasDescendant(withText(stringToBeTyped)))));
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));
    }

}
