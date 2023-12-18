package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.Model.ToDoModel;
import com.example.todolist.Utils.DataBaseHelper;
import com.example.todolist.Utils.ToDoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListner {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelper myDB;
    private List<ToDoModel> mList;
    private ToDoAdapter adapter;
    private ImageView mMenus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab);
        mMenus = findViewById(R.id.mMenus);

        //myDB = new DataBaseHelper(MainActivity.this);
        myDB = DataBaseHelper.getInstance(MainActivity.this);

        mList = new ArrayList<>();
        adapter = new ToDoAdapter(myDB , MainActivity.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        mList = myDB.toDoDAO().getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager() , AddNewTask.TAG);
            }
        });

        mMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pm = new PopupMenu(MainActivity.this, v);
                pm.getMenuInflater().inflate(R.menu.show_menu, pm.getMenu());
                PopupMenu.OnMenuItemClickListener menuItemClickListener = item -> {
                    if (item.getItemId() == R.id.sortAlpha) {
                        mList = sortByAlphabet(mList);
                        adapter.setTasks(mList);
                        adapter.notifyDataSetChanged();
                    } else if (item.getItemId() == R.id.sortTime) {
                        mList = sortByTime(mList);
                        adapter.setTasks(mList);
                        adapter.notifyDataSetChanged();
                    }
                    return true;
                };
                pm.setOnMenuItemClickListener(menuItemClickListener);
                pm.show();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    public List<ToDoModel> sortByAlphabet(List<ToDoModel> mList) {
        Collections.sort(mList, (ToDoModel o1, ToDoModel o2) -> o1.getTask().compareToIgnoreCase(o2.getTask()));
        return mList;
    }

    public List<ToDoModel> sortByTime(List<ToDoModel> mList) {
        Collections.sort(mList, (ToDoModel o1, ToDoModel o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
        return mList;
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.toDoDAO().getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();
    }

}