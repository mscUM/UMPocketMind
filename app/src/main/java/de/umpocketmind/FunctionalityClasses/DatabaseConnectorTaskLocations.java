package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eva on 25.04.16.
 */
public class DatabaseConnectorTaskLocations extends SQLiteOpenHelper {

    protected static final String DB_NAME_TASKLOCATIONS = "tasklocations.db";
    protected static final int DB_VERSION_TASKLOCATIONS = 1;
    protected static final String TABLE_TASKLOCATIONS = "tasklocations";
    protected static final String COLUMN_TASKLOCATIONS_ID = "_id";
    protected static final String COLUMN_TASKLOCATIONS_TASKID = "taskid";
    protected static final String COLUMN_TASKLOCATIONS_LOCATIONID = "locationid";

    protected static final String SQL_CREATE_TASKLOCATIONS =
            "CREATE TABLE " + TABLE_TASKLOCATIONS + "(" +
                    COLUMN_TASKLOCATIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASKLOCATIONS_TASKID + " INTEGER NOT NULL, " +
                    COLUMN_TASKLOCATIONS_LOCATIONID + " INTEGER NOT NULL);";

    protected DatabaseConnectorTaskLocations(Context context) {
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
}
