package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eva on 25.04.16.
 */
public class DatabaseConnectorTasks extends SQLiteOpenHelper {

    public static final String DB_NAME_TASKS = "tasks.db";
    public static final int DB_VERSION_TASKS = 1;
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_TASKS_ID = "_id";
    public static final String COLUMN_TASKS_NAME = "name";
    public static final String COLUMN_TASKS_DESCRIPTION = "description";
    public static final String COLUMN_TASKS_RANGE = "range";

    public static final String SQL_CREATE_LOCATIONS =
            "CREATE TABLE " + TABLE_TASKS + "(" +
                    COLUMN_TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASKS_NAME + " TEXT NOT NULL, " +
                    COLUMN_TASKS_DESCRIPTION + " TEXT NOT NULL, " +
                    COLUMN_TASKS_RANGE + " REAL NOT NULL);";

    public DatabaseConnectorTasks(Context context) {
        super(context, DB_NAME_TASKS, null, DB_VERSION_TASKS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_LOCATIONS);
        } catch (Exception ex) {
            // Todo
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Todo
    }

    public void insertTaskInfo() {
        // Todo
    }

    public void selectTaskInfo() {
        // Todo
    }

    public void updateTaskInfo() {
        // Todo
    }

    public void deleteTaskInfo() {
        // Todo
    }
}
