package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eva on 24.04.16.
 */
public class DatabaseConnectorLocations extends SQLiteOpenHelper {

    public static final String DB_NAME_LOCATIONS = "locations.db";
    public static final int DB_VERSION_LOCATIONS = 1;
    public static final String TABLE_LOCATIONS = "locations";
    public static final String COLUMN_LOCATIONS_ID = "_id";
    public static final String COLUMN_LOCATIONS_NAME = "name";
    public static final String COLUMN_LOCATIONS_DESCRIPTION = "description";
    public static final String COLUMN_LOCATIONS_LONGTITUDE = "longtitude";
    public static final String COLUMN_LOCATIONS_LATITUDE = "latitude";

    public static final String SQL_CREATE_LOCATIONS =
            "CREATE TABLE " + TABLE_LOCATIONS + "(" +
                    COLUMN_LOCATIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LOCATIONS_NAME + " TEXT NOT NULL, " +
                    COLUMN_LOCATIONS_DESCRIPTION + " TEXT NOT NULL, " +
                    COLUMN_LOCATIONS_LONGTITUDE + " REAL NOT NULL, " +
                    COLUMN_LOCATIONS_LATITUDE + " REAL NOT NULL);";

    public DatabaseConnectorLocations(Context context) {
        super(context, DB_NAME_LOCATIONS, null, DB_VERSION_LOCATIONS);
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
}
