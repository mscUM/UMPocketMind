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

    private SQLiteDatabase database;
    private DatabaseConnectorTasks databaseConnectorTasks;
    private String[] columns = {
            DatabaseConnectorTasks.COLUMN_TASKS_ID,
            DatabaseConnectorTasks.COLUMN_TASKS_NAME,
            DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION,
            DatabaseConnectorTasks.COLUMN_TASKS_RANGE,
    };


    public TaskManager(Context context) {
        databaseConnectorTasks = new DatabaseConnectorTasks(context);
    }

    public void open() {
        database = databaseConnectorTasks.getWritableDatabase();
    }

    public void close() {
        databaseConnectorTasks.close();
    }

    public Task createTask(Task newTask) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConnectorTasks.COLUMN_TASKS_NAME, newTask.getName());
        values.put(DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION, newTask.getDescription());
        values.put(DatabaseConnectorTasks.COLUMN_TASKS_RANGE, newTask.getRange());

        long insertId = database.insert(DatabaseConnectorTasks.TABLE_TASKS, null, values);

        Cursor cursor = database.query(DatabaseConnectorTasks.TABLE_TASKS,
                columns, DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Task task = cursorToTask(cursor);
        cursor.close();

        return task;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        Cursor cursor = database.query(DatabaseConnectorTasks.TABLE_TASKS,
                columns, null, null, null, null, null);

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
        ContentValues values = new ContentValues();
        values.put(DatabaseConnectorTasks.COLUMN_TASKS_NAME, newTask.getName());
        values.put(DatabaseConnectorTasks.COLUMN_TASKS_DESCRIPTION, newTask.getDescription());
        values.put(DatabaseConnectorTasks.COLUMN_TASKS_RANGE, newTask.getRange());

        database.update(DatabaseConnectorTasks.TABLE_TASKS,
                values,
                DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + newTask.getId(),
                null);
    }

    public void deleteTaskById(long id) {
        database.delete(DatabaseConnectorTasks.TABLE_TASKS,
                DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + id,
                null);
    }

    public Task getTaskById(long id) {
        Cursor cursor = database.query(DatabaseConnectorTasks.TABLE_TASKS,
                columns, DatabaseConnectorTasks.COLUMN_TASKS_ID + "=" + id,
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

        return task;
    }
}
