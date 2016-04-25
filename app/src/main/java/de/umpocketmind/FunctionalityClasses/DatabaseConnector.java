package de.umpocketmind.FunctionalityClasses;

/**
 * Created by eva on 24.04.16.
 */
public class DatabaseConnector {

    private static DatabaseConnector databaseConnector;

    public static DatabaseConnector getDatabaseConnector() {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }
        return databaseConnector;
    }

    public void insertTaskInfo() {
        // Todo
    }

    public void insertLocationInfo() {
        // Todo
    }

    public void selectTaskInfo() {
        // Todo
    }

    public void selectLocationInfo() {
        // Todo
    }

    public void updateTaskInfo() {
        // Todo
    }

    public void updateLocationInfo() {
        // Todo
    }

    public void deleteTaskInfo() {
        // Todo
    }

    public void deleteLocationInfo() {
        // Todo
    }
}
