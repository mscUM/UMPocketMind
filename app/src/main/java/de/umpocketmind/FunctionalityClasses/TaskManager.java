package de.umpocketmind.FunctionalityClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

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


    public TaskManager(Context context) {
        databaseConnectorTasks = new DatabaseConnectorTasks(context);
        taskLocationsManager = new TaskLocationsManager(context);
    }

    public void open() {
        taskDatabase = databaseConnectorTasks.getWritableDatabase();
        taskLocationsManager.open();
    }

    public void close() {
        databaseConnectorTasks.close();
        taskLocationsManager.close();
    }

    public Task createTask(Task newTask) {
        ContentValues taskValues = new ContentValues();
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_NAME, newTask.getName());
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION, newTask.getDescription());
        taskValues.put(DatabaseConnectorTasks.COLUMN_TASKS_RANGE, newTask.getRange());

        long insertTaskId = taskDatabase.insert(DatabaseConnectorTasks.TABLE_TASKS, null, taskValues);

        for (Location location: newTask.getLocations()) {
            taskLocationsManager.createTaskLocation(newTask, location);
        }

        Task task = getTaskById(insertTaskId);
        return task;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        Cursor cursor = taskDatabase.query(DatabaseConnectorTasks.TABLE_TASKS,
                taskColumns, null, null, null, null, null);

        cursor.moveToFirst();
        Task task;

        while(!cursor.isAfterLast()) {
            task = cursorToTask(cursor);
            taskList.add(task);
            cursor.moveToNext();
        }

        cursor.close();

        return taskList;
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
    }

    public Task getTaskById(long id) {
        Cursor cursor = taskDatabase.query(DatabaseConnectorTasks.TABLE_TASKS,
                taskColumns, DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Task task = cursorToTask(cursor);
        cursor.close();

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
        int range = cursor.getInt(rangeIndex);

        Task task = new Task(id, name, description, range);

        ArrayList<Location> locations = taskLocationsManager.getAllTaskLocationsForTask(task);
        for (Location location: locations) {
            task.addLocationToTask(location);
        }

        return task;
    }
}
