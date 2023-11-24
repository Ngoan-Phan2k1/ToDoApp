package com.example.todolist.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolist.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

//public class DataBaseHelper extends SQLiteOpenHelper {
//
//    private SQLiteDatabase db;
//
//    private static  final String DATABASE_NAME = "TODO_DATABASE";
//    private static  final String TABLE_NAME = "TODO_TABLE";
//    private static  final String COL_1 = "ID";
//    private static  final String COL_2 = "TASK";
//    private static  final String COL_3 = "STATUS";
//
//    public DataBaseHelper(@Nullable Context context ) {
//        super(context, DATABASE_NAME, null, 1);
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , TASK TEXT , STATUS INTEGER)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    public void insertTask(ToDoModel model){
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_2 , model.getTask());
//        values.put(COL_3 , 0);
//        db.insert(TABLE_NAME , null , values);
//    }
//
//    public void updateTask(int id , String task){
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_2 , task);
//        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
//    }
//
//    public void updateStatus(int id , int status){
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_3 , status);
//        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
//    }
//
//    public void deleteTask(int id ){
//        db = this.getWritableDatabase();
//        db.delete(TABLE_NAME , "ID=?" , new String[]{String.valueOf(id)});
//    }
//
//    public List<ToDoModel> getAllTasks(){
//
//        db = this.getWritableDatabase();
//        Cursor cursor = null;
//        List<ToDoModel> modelList = new ArrayList<>();
//
//        db.beginTransaction();
//        try {
//            cursor = db.query(TABLE_NAME , null , null , null , null , null , null);
//            if (cursor !=null){
//                if (cursor.moveToFirst()){
//                    int idColumnIndex = cursor.getColumnIndex(COL_1);
//                    int taskColumnIndex = cursor.getColumnIndex(COL_2);
//                    int statusColumnIndex = cursor.getColumnIndex(COL_3);
//                    do {
//                        ToDoModel task = new ToDoModel();
//                        if (idColumnIndex != -1) {
//                            task.setId(cursor.getInt(idColumnIndex));
//                        }
//
//                        if (taskColumnIndex != -1) {
//                            task.setTask(cursor.getString(taskColumnIndex));
//                        }
//
//                        if (statusColumnIndex != -1) {
//                            task.setStatus(cursor.getInt(statusColumnIndex));
//                        }
////                        task.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
////                        task.setTask(cursor.getString(cursor.getColumnIndex(COL_2)));
////                        task.setStatus(cursor.getInt(cursor.getColumnIndex(COL_3)));
//                        modelList.add(task);
//
//                    }while (cursor.moveToNext());
//                }
//            }
//        }finally {
//            db.endTransaction();
//            cursor.close();
//        }
//        return modelList;
//    }
//
//}

@Database(entities = {ToDoModel.class}, version = 1)
public abstract class DataBaseHelper extends RoomDatabase {
    private static  final String DATABASE_NAME = "TODO_DATABASE";
    private static DataBaseHelper dataBaseHelper;

    public static DataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null) {
            dataBaseHelper = Room.databaseBuilder(context.getApplicationContext(),
                            DataBaseHelper.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return dataBaseHelper;
    }
    public abstract ToDoDAO toDoDAO();
}
