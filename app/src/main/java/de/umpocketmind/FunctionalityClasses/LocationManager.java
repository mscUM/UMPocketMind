package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

/**
 * Created by eva on 24.04.16.
 */
public class LocationManager {

    private SQLiteDatabase database;
    private DatabaseConnector databaseConnector;
    private String[] columns = {
            DatabaseConnector.COLUMN_LOCATIONS_ID,
            DatabaseConnector.COLUMN_LOCATIONS_NAME,
            DatabaseConnector.COLUMN_LOCATIONS_DESCRIPTION,
            DatabaseConnector.COLUMN_LOCATIONS_LONGTITUDE,
            DatabaseConnector.COLUMN_LOCATIONS_LATITUDE
    };


    public LocationManager(Context context) {
        databaseConnector = new DatabaseConnector(context);
    }

    public void open() {
        database = databaseConnector.getWritableDatabase();
    }

    public void close() {
        databaseConnector.close();
    }

    public Location createLocation(Location newLocation) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConnector.COLUMN_LOCATIONS_NAME, newLocation.getName());
        values.put(DatabaseConnector.COLUMN_LOCATIONS_DESCRIPTION, newLocation.getDescription());
        values.put(DatabaseConnector.COLUMN_LOCATIONS_LONGTITUDE, newLocation.getLongtitude());
        values.put(DatabaseConnector.COLUMN_LOCATIONS_LATITUDE, newLocation.getLatitude());

        long insertId = database.insert(DatabaseConnector.TABLE_LOCATIONS, null, values);

        Cursor cursor = database.query(DatabaseConnector.TABLE_LOCATIONS,
                columns, DatabaseConnector.COLUMN_LOCATIONS_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Location location = cursorToLocation(cursor);
        cursor.close();

        return location;
    }

    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> locationList = new ArrayList<>();

        Cursor cursor = database.query(DatabaseConnector.TABLE_LOCATIONS,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Location location;

        while(!cursor.isAfterLast()) {
            location = cursorToLocation(cursor);
            locationList.add(location);
            cursor.moveToNext();
        }

        cursor.close();

        return locationList;
    }

    public void updateLocation(Location newLocation) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConnector.COLUMN_LOCATIONS_NAME, newLocation.getName());
        values.put(DatabaseConnector.COLUMN_LOCATIONS_DESCRIPTION, newLocation.getDescription());
        values.put(DatabaseConnector.COLUMN_LOCATIONS_LONGTITUDE, newLocation.getLongtitude());
        values.put(DatabaseConnector.COLUMN_LOCATIONS_LATITUDE, newLocation.getLatitude());

        database.update(DatabaseConnector.TABLE_LOCATIONS,
                values,
                DatabaseConnector.COLUMN_LOCATIONS_ID + "=" + newLocation.getId(),
                null);
    }

    public void deleteLocationById(long id) {
        database.delete(DatabaseConnector.TABLE_LOCATIONS,
                DatabaseConnector.COLUMN_LOCATIONS_ID + "=" + id,
                null);
    }

    public Location getLocationById(long id) {
        Cursor cursor = database.query(DatabaseConnector.TABLE_LOCATIONS,
                columns, DatabaseConnector.COLUMN_LOCATIONS_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Location location = cursorToLocation(cursor);
        cursor.close();

        return location;
    }

    private Location cursorToLocation(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatabaseConnector.COLUMN_LOCATIONS_ID);
        int idName = cursor.getColumnIndex(DatabaseConnector.COLUMN_LOCATIONS_NAME);
        int idDescription = cursor.getColumnIndex(DatabaseConnector.COLUMN_LOCATIONS_DESCRIPTION);
        int idLongtitude = cursor.getColumnIndex(DatabaseConnector.COLUMN_LOCATIONS_LONGTITUDE);
        int idLatitude = cursor.getColumnIndex(DatabaseConnector.COLUMN_LOCATIONS_LATITUDE);

        long id = cursor.getLong(idIndex);
        String name = cursor.getString(idName);
        String description = cursor.getString(idDescription);
        int longtitude = cursor.getInt(idLongtitude);
        int latitude = cursor.getInt(idLatitude);

        Location location = new Location(id, name, description, longtitude, latitude);

        return location;
    }
}
