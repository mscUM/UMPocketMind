package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;

/**
 * Created by eva on 24.04.16.
 */
public class TaskManager {

    private SQLiteDatabase taskDatabase;
    private DatabaseConnectorTasks databaseConnectorTasks;
    private String[] taskColumns = {
            DatabaseConnectorTasks.COLUMN_TASKS_ID,
            DatabaseConnectorTasks.COLUMN_TASKS_NAME,
            DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION,
            DatabaseConnectorTasks.COLUMN_TASKS_RANGE,
    };
    private TaskLocationsManager taskLocationsManager;

    private Task mockTaskBuyIceCream;
    private Task mockTaskVisitSidney;
    private Location mockLocationForIceCreamParadeplatz;
    private Location mockLocationForIceCreamWasserturm;
    private Location mockLocationForVisitSidney;

    private void insertMockTasksIntoDatabase() {
        // Add Mock Locations
        Location locationForIceCreamParadeplatz = new Location(0, "ParadeplatzFürTask", "Paradeplatz für Task", 8.4660542, 49.4874131);
        Location locationForIceCreamWasserturm = new Location(0, "WasserturmFürTask", "Wasserturm für Task", 8.4733678, 49.4840612);
        Location locationForVisitSidney = new Location(0, "SidneyFürTask", "Sidney für Task", 151.2069902, -33.8674869);
        mockLocationForIceCreamParadeplatz = taskLocationsManager.locationManager.createLocation(locationForIceCreamParadeplatz);
        mockLocationForIceCreamWasserturm = taskLocationsManager.locationManager.createLocation(locationForIceCreamWasserturm);
        mockLocationForVisitSidney = taskLocationsManager.locationManager.createLocation(locationForVisitSidney);
        // Add Mock Tasks
        Task buyIceCream = new Task(0, "Ice Cream", "Buy some ice cream", 500);
        buyIceCream.addLocationToTask(mockLocationForIceCreamParadeplatz);
        buyIceCream.addLocationToTask(mockLocationForIceCreamWasserturm);
        Log.i("Testdata mit Locations:", buyIceCream.toString());
        mockTaskBuyIceCream = createTask(buyIceCream);
        Task visitSidney = new Task(0, "Visit Sidney", "Visit the awesome city of Sidney", 5000);
        visitSidney.addLocationToTask(mockLocationForVisitSidney);
        mockTaskVisitSidney = createTask(visitSidney);
        Log.i("LocationMgr", "Mock tasks added to database.");
    }

    private void deleteMockTasksFromDatabase() {
        taskLocationsManager.locationManager.deleteLocationById(mockLocationForIceCreamParadeplatz.getId());
        taskLocationsManager.locationManager.deleteLocationById(mockLocationForIceCreamWasserturm.getId());
        taskLocationsManager.locationManager.deleteLocationById(mockLocationForVisitSidney.getId());
        deleteTaskById(mockTaskBuyIceCream.getId());
        deleteTaskById(mockTaskVisitSidney.getId());
        Log.i("LocationMgr", "Mock tasks deleted from database.");
    }


    public TaskManager(Context context) {
        databaseConnectorTasks = new DatabaseConnectorTasks(context);
        taskLocationsManager = new TaskLocationsManager(context);
        Log.i("TaskMgr", "TaskManager created.");
    }

    public void open() {
        taskDatabase = databaseConnectorTasks.getWritableDatabase();
        taskLocationsManager.open();
        Log.i("TaskMgr", "Database opened.");
        insertMockTasksIntoDatabase();
    }

    public void close() {
        deleteMockTasksFromDatabase();
        databaseConnectorTasks.close();
        taskLocationsManager.close();
        Log.i("TaskMgr", "Database closed.");
    }

    public Task createTask(Task newTask) {
        ContentValues taskValues = new ContentValues();
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_NAME, newTask.getName());
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION, newTask.getDescription());
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_RANGE, newTask.getRange());

        long insertTaskId = taskDatabase.insert(DatabaseConnectorTasks.TABLE_TASKS, null, taskValues);
        Log.i("TaskMgr", "Task inserted into Database.");
        Log.i("Test2", newTask.getLocations().toString());
        for (Location location: newTask.getLocations()) {
            taskLocationsManager.createTaskLocation(newTask, location);
        }

        return getTaskById(insertTaskId);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        Cursor cursor = taskDatabase.query(DatabaseConnectorTasks.TABLE_TASKS,
                taskColumns, null, null, null, null, null);

        cursor.moveToFirst();
        Task task;

        while(!cursor.isAfterLast()) {
            task = cursorToTask(cursor);

            //Add locations to that task
            ArrayList<Location> taskLocationList = taskLocationsManager.getAllTaskLocationsForTask(task);
            for (Location taskLocation: taskLocationList) {
                task.addLocationToTask(taskLocation);
            }

            taskList.add(task);
            Log.i("2############", task.toString());
            cursor.moveToNext();
        }

        cursor.close();
        Log.i("TaskMgr", "All Tasks fetched from Database.");

        return taskList;
    }

    public ArrayList<Task> sortTasksByDistance(ArrayList<Task> taskList, double longtitude, double latitude) {
        ArrayList<Task> sortedTaskList = taskList;
        float[] results = new float[1];

        while (!taskList.isEmpty()) {
            // initialize nearest Task
            Task nearestTask = null;
            float nearestTaskDistance = -1;
            // go through list of tasks to determine nearest task
            for (Task task: taskList) {
                // initialize nearest location of a task
                Location nearestLocation = null;
                float nearestLocationDistance = -1;
                // go through list of locations to determine nearest location of a task
                for (Location taskLocation: task.getLocations()) {
                    android.location.Location.distanceBetween(latitude, longtitude, taskLocation.getLatitude(), taskLocation.getLongtitude(), results);
                    if (nearestLocationDistance < 0 || results[0] < nearestLocationDistance) {
                        nearestLocation = taskLocation;
                        nearestLocationDistance = results[0];
                    }
                }
                if (nearestTaskDistance < 0 || nearestLocationDistance < nearestTaskDistance) {
                    nearestTask = task;
                    nearestTaskDistance = nearestLocationDistance;
                }
            }
            sortedTaskList.add(nearestTask);
            taskList.remove(nearestTask);
        }

        return sortedTaskList;
    }

    public boolean isUserPositionInTaskRange(Task task, double longtitude, double latitude) {
        boolean userPositionIsInTaskRange = false;
        float[] results = new float[1];

        for (Location location: task.getLocations()) {
            android.location.Location.distanceBetween(latitude, longtitude, location.getLatitude(), location.getLongtitude(), results);
            if (results[0] <= task.getRange()) {
                userPositionIsInTaskRange = true;
                break;
            }
        }

        return userPositionIsInTaskRange;
    }

    public void updateTask(Task newTask) {
        ContentValues taskValues = new ContentValues();
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_NAME, newTask.getName());
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION, newTask.getDescription());
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_RANGE, newTask.getRange());

        taskDatabase.update(DatabaseConnectorTasks.TABLE_TASKS,
                taskValues,
                DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + newTask.getId(),
                null);
        Log.i("TaskMgr", "Task updated in Database. Id: " + newTask.getId());

        taskLocationsManager.deleteAllTaskLocationsForTask(newTask);
        for (Location location: newTask.getLocations()) {
            taskLocationsManager.createTaskLocation(newTask, location);
        }
    }

    public void deleteTaskById(long id) {
        Task task = getTaskById(id);
        taskLocationsManager.deleteAllTaskLocationsForTask(task);
        taskDatabase.delete(DatabaseConnectorTasks.TABLE_TASKS,
                DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + id,
                null);
        Log.i("TaskMgr", "Task deleted from Database. Id: " +id);
    }

    public Task getTaskById(long id) {
        Cursor cursor = taskDatabase.query(DatabaseConnectorTasks.TABLE_TASKS,
                taskColumns, DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Task task = cursorToTask(cursor);
        cursor.close();

        //Add locations to that task
        ArrayList<Location> taskLocationList = taskLocationsManager.getAllTaskLocationsForTask(task);
        for (Location taskLocation: taskLocationList) {
            task.addLocationToTask(taskLocation);
        }
        Log.i("#############", task.toString());
        Log.i("TaskMgr", "Task fetched from Database. Id: " +id);

        return task;
    }

    private Task cursorToTask(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatabaseConnectorTasks.COLUMN_TASKS_ID);
        int nameIndex = cursor.getColumnIndex(DatabaseConnectorTasks.COLUMN_TASKS_NAME);
        int descriptionIndex = cursor.getColumnIndex(DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION);
        int rangeIndex = cursor.getColumnIndex(DatabaseConnectorTasks.COLUMN_TASKS_RANGE);

        long id = cursor.getLong(idIndex);
        String name = cursor.getString(nameIndex);
        String description = cursor.getString(descriptionIndex);
        double range = cursor.getDouble(rangeIndex);

        Task task = new Task(id, name, description, range);

        ArrayList<Location> locations = taskLocationsManager.getAllTaskLocationsForTask(task);
        for (Location location: locations) {
            task.addLocationToTask(location);
        }

        return task;
    }
}
