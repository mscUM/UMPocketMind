package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by eva on 25.04.16.
 */
public class DatabaseConnectorTasks extends SQLiteOpenHelper {

    protected static final String DB_NAME_TASKS = "tasks.db";
    protected static final int DB_VERSION_TASKS = 1;
    protected static final String TABLE_TASKS = "tasks";
    protected static final String COLUMN_TASKS_ID = "_id";
    protected static final String COLUMN_TASKS_NAME = "name";
    protected static final String COLUMN_TASKS_DESCRIPTION = "description";
    protected static final String COLUMN_TASKS_RANGE = "range";

    protected static final String SQL_CREATE_LOCATIONS =
            "CREATE TABLE " + TABLE_TASKS + "(" +
                    COLUMN_TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASKS_NAME + " TEXT NOT NULL, " +
                    COLUMN_TASKS_DESCRIPTION + " TEXT NOT NULL, " +
                    COLUMN_TASKS_RANGE + " REAL NOT NULL);";

    protected DatabaseConnectorTasks(Context context) {
        super(context, DB_NAME_TASKS, null, DB_VERSION_TASKS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean exceptionThrown = false;

        try {
            db.execSQL(SQL_CREATE_LOCATIONS);
        } catch (Exception ex) {
            exceptionThrown = true;
        }

        if (exceptionThrown) {
            Log.d("DBConTasks", "Creation of Database not possible.");
        } else {
            Log.i("DBConTasks", "Creation of Database succeeded.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBConTasks", "Update of Database.");
    }
}
