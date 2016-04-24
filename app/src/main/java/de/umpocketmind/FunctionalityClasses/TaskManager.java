package de.umpocketmind.FunctionalityClasses;

import java.util.ArrayList;

/**
 * Created by eva on 24.04.16.
 */
public class TaskManager {

    private static TaskManager taskManager;

    public static TaskManager getTaskManager() {
        if (taskManager == null) {
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    public void createTask() {
        // Todo
    }

    public void updateTaskById(int id) {
        // Todo
    }

    public void deleteTaskById(int id) {
        // Todo
    }

    public void getTaskById(int id) {
        // Todo
    }

    public void getAllTasks() {
        // Todo
    }
}
