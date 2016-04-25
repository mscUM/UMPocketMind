package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eva on 25.04.16.
 */
public class DatabaseConnectorTaskLocations extends SQLiteOpenHelper {

    public static final String DB_NAME_TASKLOCATIONS = "tasklocations.db";
    public static final int DB_VERSION_TASKLOCATIONS = 1;
    public static final String TABLE_TASKLOCATIONS = "tasklocations";
    public static final String COLUMN_TASKLOCATIONS_ID = "_id";
    public static final String COLUMN_TASKLOCATIONS_TASKID = "taskid";
    public static final String COLUMN_TASKLOCATIONS_LOCATIONID = "locationid";

    public static final String SQL_CREATE_TASKLOCATIONS =
            "CREATE TABLE " + TABLE_TASKLOCATIONS + "(" +
                    COLUMN_TASKLOCATIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASKLOCATIONS_TASKID + " INTEGER NOT NULL, " +
                    COLUMN_TASKLOCATIONS_LOCATIONID + " INTEGER NOT NULL);";

    public DatabaseConnectorTaskLocations(Context context) {
        super(context, DB_NAME_TASKLOCATIONS, null, DB_VERSION_TASKLOCATIONS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_TASKLOCATIONS);
        } catch (Exception ex) {
            // Todo
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Todo
    }

    public void insertTaskLocationsInfo() {
        // Todo
    }

    public void selectTaskLocationsInfo() {
        // Todo
    }

    public void updateTaskLocationsInfo() {
        // Todo
    }

    public void deleteTaskLocationsInfo() {
        // Todo
    }
}
