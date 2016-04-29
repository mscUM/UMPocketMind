package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by eva on 24.04.16.
 */
public class DatabaseConnectorLocations extends SQLiteOpenHelper {

    protected static final String DB_NAME_LOCATIONS = "locations.db";
    protected static final int DB_VERSION_LOCATIONS = 1;
    protected static final String TABLE_LOCATIONS = "locations";
    protected static final String COLUMN_LOCATIONS_ID = "_id";
    protected static final String COLUMN_LOCATIONS_NAME = "name";
    protected static final String COLUMN_LOCATIONS_DESCRIPTION = "description";
    protected static final String COLUMN_LOCATIONS_LONGTITUDE = "longtitude";
    protected static final String COLUMN_LOCATIONS_LATITUDE = "latitude";

    protected static final String SQL_CREATE_LOCATIONS =
            "CREATE TABLE " + TABLE_LOCATIONS + "(" +
                    COLUMN_LOCATIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LOCATIONS_NAME + " TEXT NOT NULL, " +
                    COLUMN_LOCATIONS_DESCRIPTION + " TEXT NOT NULL, " +
                    COLUMN_LOCATIONS_LONGTITUDE + " REAL NOT NULL, " +
                    COLUMN_LOCATIONS_LATITUDE + " REAL NOT NULL);";

    protected DatabaseConnectorLocations(Context context) {
        super(context, DB_NAME_LOCATIONS, null, DB_VERSION_LOCATIONS);
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
            Log.d("DBConLocations", "Creation of Database not possible.");
        } else {
            Log.i("DBConLocations", "Creation of Database succeeded.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBConLocations", "Update of Database.");
    }
}
