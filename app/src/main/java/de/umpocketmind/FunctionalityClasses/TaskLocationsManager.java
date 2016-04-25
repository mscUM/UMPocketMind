package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

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
    private Context context;


    public TaskLocationsManager(Context context) {
        this.context = context;
        databaseConnectorTaskLocations = new DatabaseConnectorTaskLocations(context);
    }

    public void open() {
        taskLocationsDatabase = databaseConnectorTaskLocations.getWritableDatabase();
    }

    public void close() {
        databaseConnectorTaskLocations.close();
    }

    public void createTaskLocation(Task task, Location location) {
        ContentValues taskLocationValues = new ContentValues();
        taskLocationValues.put(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_TASKID, task.getId());
        taskLocationValues.put(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_LOCATIONID, location.getId());

        long insertTaskLocationId = taskLocationsDatabase.insert(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS, null, taskLocationValues);
    }

    public ArrayList<Location> getAllTaskLocationsForTask(Task task) {
        ArrayList<Location> locationList = new ArrayList<>();

        Cursor cursor = taskLocationsDatabase.query(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS,
                taskLocationsColumns, null, null, null, null, null);

        cursor.moveToFirst();

        Location location;

        while(!cursor.isAfterLast()) {
            location = cursorToTaskLocation(cursor);
            locationList.add(location);
            cursor.moveToNext();
        }

        cursor.close();

        return locationList;
    }

    public void deleteAllTaskLocationsForTask(Task task) {

        Cursor cursor = taskLocationsDatabase.query(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS,
                taskLocationsColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            int idIndex = cursor.getColumnIndex(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_ID);
            long id = cursor.getLong(idIndex);
            deleteTaskLocationById(id);
            cursor.moveToNext();
        }

        cursor.close();
    }

    public void deleteTaskLocationById(long id) {
        taskLocationsDatabase.delete(DatabaseConnectorTaskLocations.TABLE_TASKLOCATIONS,
                DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_ID + "=" + id,
                null);
    }

    private Location cursorToTaskLocation(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_ID);
        int taskIdIndex = cursor.getColumnIndex(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_TASKID);
        int locationIdIndex = cursor.getColumnIndex(DatabaseConnectorTaskLocations.COLUMN_TASKLOCATIONS_LOCATIONID);

        long id = cursor.getLong(idIndex);
        long taskId = cursor.getLong(taskIdIndex);
        long locationId = cursor.getLong(locationIdIndex);

        LocationManager locationManager = new LocationManager(context);
        Location location = locationManager.getLocationById(locationId);

        return location;
    }
}
