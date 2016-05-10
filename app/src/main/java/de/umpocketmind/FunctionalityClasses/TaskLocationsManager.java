package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import android.util.Log;

/**
 * Created by eva on 25.04.16.
 */
public class TaskLocationsManager {

    private SQLiteDatabase taskLocationsDatabase;
    private DatabaseConnectorTaskLocations databaseConnectorTaskLocations;
    private String[] taskLocationsColumns = {
            DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_ID,
            DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_TASKID,
            DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_LOCATIONID,
    };
    protected LocationManager locationManager;


    protected TaskLocationsManager(Context context) {
        databaseConnectorTaskLocations = new DatabaseConnectorTaskLocations(context);
        locationManager = new LocationManager(context);
        Log.i("TaskLocationsMgr", "TaskLocationsManager created.");
    }

    protected void open() {
        taskLocationsDatabase = databaseConnectorTaskLocations.getWritableDatabase();
        locationManager.open();
        Log.i("TaskLocationsMgr", "Database opened.");
    }

    protected void close() {
        databaseConnectorTaskLocations.close();
        locationManager.close();
        Log.i("TaskLocationsMgr", "Database closed.");
    }

    protected void createTaskLocation(Task task, Location location) {
        ContentValues taskLocationValues = new ContentValues();
        taskLocationValues.put(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_TASKID, task.getId());
        taskLocationValues.put(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_LOCATIONID, location.getId());

        taskLocationsDatabase.insert(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS, null, taskLocationValues);
        Log.i("TaskLocationsMgr", "TaskLocation inserted into Database.");
    }

    protected ArrayList<Location> getAllTaskLocationsForTask(Task task) {
        ArrayList<Location> locationList = new ArrayList<>();

        Cursor cursor = taskLocationsDatabase.query(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS,
                taskLocationsColumns, DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_TASKID + "=" + task.getId(), null, null, null, null);

        cursor.moveToFirst();

        Location location;

        while(!cursor.isAfterLast()) {
            location = cursorToTaskLocation(cursor);
            locationList.add(location);
            cursor.moveToNext();
        }

        cursor.close();
        Log.i("TaskLocationsMgr", "All TaskLocations fetched from Database for Task.");

        return locationList;
    }

    protected void deleteAllTaskLocationsForTask(Task task) {

        Cursor cursor = taskLocationsDatabase.query(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS,
                taskLocationsColumns, DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_TASKID + "=" + task.getId(), null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            int idIndex = cursor.getColumnIndex(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_ID);
            long id = cursor.getLong(idIndex);
            deleteTaskLocationById(id);
            cursor.moveToNext();
        }

        cursor.close();
        Log.i("TaskLocationsMgr", "All TaskLocations deleted from Database for Task.");
    }

    protected void deleteTaskLocationById(long id) {
        taskLocationsDatabase.delete(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS,
                DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_ID + "=" + id,
                null);
        Log.i("TaskLocationsMgr", "TaskLocation deleted from Database. Id: " + id);
    }

    private Location cursorToTaskLocation(Cursor cursor) {
        int locationIdIndex = cursor.getColumnIndex(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_LOCATIONID);

        long locationId = cursor.getLong(locationIdIndex);

        return locationManager.getLocationById(locationId);
    }
}
