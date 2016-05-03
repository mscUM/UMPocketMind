package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import android.util.Log;

/**
 * Created by eva on 24.04.16.
 */
public class LocationManager {

    private SQLiteDatabase database;
    private DatabaseConnectorLocations databaseConnectorLocations;
    private String[] columns = {
            DatabaseConnectorLocations.COLUMN_LOCATIONS_ID,
            DatabaseConnectorLocations.COLUMN_LOCATIONS_NAME,
            DatabaseConnectorLocations.COLUMN_LOCATIONS_DESCRIPTION,
            DatabaseConnectorLocations.COLUMN_LOCATIONS_LONGTITUDE,
            DatabaseConnectorLocations.COLUMN_LOCATIONS_LATITUDE
    };

    public void insertMockDataIntoDatabase() {
        Location locationParadeplatz = new Location(0, "Paradeplatz", "This is Paradeplatz", 8.4660542, 49.4874131);
        createLocation(locationParadeplatz);
        Location locationSidney = new Location(0, "Sidney", "This is Sidney", 151.2069902, -33.8674869);
        createLocation(locationSidney);
    }

    public void deleteAllLocationsFromDatabase() {
        ArrayList<Location> locations = getAllLocations();
        for (Location location: locations) {
            deleteLocationById(location.getId());
        }
    }


    public LocationManager(Context context) {
        databaseConnectorLocations = new DatabaseConnectorLocations(context);
        Log.i("LocationMgr", "LocationManager created.");
    }

    public void open() {
        database = databaseConnectorLocations.getWritableDatabase();
        Log.i("LocationMgr", "Database opened.");
    }

    public void close() {
        databaseConnectorLocations.close();
        Log.i("LocationMgr", "Database closed.");
    }

    public Location createLocation(Location newLocation) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_NAME, newLocation.getName());
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_DESCRIPTION, newLocation.getDescription());
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_LONGTITUDE, newLocation.getLongtitude());
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_LATITUDE, newLocation.getLatitude());

        long insertId = database.insert(DatabaseConnectorLocations.TABLE_LOCATIONS, null, values);
        Log.i("LocationMgr", "Location inserted into Database.");

        Location location = getLocationById(insertId);
        return location;
    }

    public ArrayList<Location> getAllLocations() {
        Log.i("LocationMgr", "Method getAllLocation called");
        ArrayList<Location> locationList = new ArrayList<>();

        Cursor cursor = database.query(DatabaseConnectorLocations.TABLE_LOCATIONS,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Location location;

        while(!cursor.isAfterLast()) {
            location = cursorToLocation(cursor);
            locationList.add(location);
            cursor.moveToNext();
        }

        cursor.close();
        Log.i("LocationMgr", "All Locations fetched from Database.");

        return locationList;
    }

    public void updateLocation(Location newLocation) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_NAME, newLocation.getName());
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_DESCRIPTION, newLocation.getDescription());
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_LONGTITUDE, newLocation.getLongtitude());
        values.put(DatabaseConnectorLocations.COLUMN_LOCATIONS_LATITUDE, newLocation.getLatitude());

        database.update(DatabaseConnectorLocations.TABLE_LOCATIONS,
                values,
                DatabaseConnectorLocations.COLUMN_LOCATIONS_ID + "=" + newLocation.getId(),
                null);
        Log.i("LocationMgr", "Location updated in Database. Id: " +newLocation.getId());
    }

    public void deleteLocationById(long id) {
        database.delete(DatabaseConnectorLocations.TABLE_LOCATIONS,
                DatabaseConnectorLocations.COLUMN_LOCATIONS_ID + "=" + id,
                null);
        Log.i("LocationMgr", "Location deleted from Database. Id: " + id);
    }

    public Location getLocationById(long id) {
        Cursor cursor = database.query(DatabaseConnectorLocations.TABLE_LOCATIONS,
                columns, DatabaseConnectorLocations.COLUMN_LOCATIONS_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Location location = cursorToLocation(cursor);
        cursor.close();
        Log.i("LocationMgr", "Location fetched from Database. Id: " + id);

        return location;
    }

    private Location cursorToLocation(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatabaseConnectorLocations.COLUMN_LOCATIONS_ID);
        int idName = cursor.getColumnIndex(DatabaseConnectorLocations.COLUMN_LOCATIONS_NAME);
        int idDescription = cursor.getColumnIndex(DatabaseConnectorLocations.COLUMN_LOCATIONS_DESCRIPTION);
        int idLongtitude = cursor.getColumnIndex(DatabaseConnectorLocations.COLUMN_LOCATIONS_LONGTITUDE);
        int idLatitude = cursor.getColumnIndex(DatabaseConnectorLocations.COLUMN_LOCATIONS_LATITUDE);

        long id = cursor.getLong(idIndex);
        String name = cursor.getString(idName);
        String description = cursor.getString(idDescription);
        int longtitude = cursor.getInt(idLongtitude);
        int latitude = cursor.getInt(idLatitude);

        Location location = new Location(id, name, description, longtitude, latitude);

        return location;
    }
}
