package de.umpocketmind;

import java.util.ArrayList;

/**
 * Created by eva on 24.04.16.
 */
public class Task {

    private int id;                         // Unique identifier
    private String name;                    // Task Name
    private String description;             // Task Description
    private double range;                   // Range to notify user
    private ArrayList<Location> locations;  // Locations added to this task

    // Constructor
    public Task(int id, String name, String description, double range) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.range = range;
        this.locations = new ArrayList<>();
    }

    // Getter and Setter of simple attributes
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getRange() {
        return range;
    }
    public void setRange(double range) {
        this.range = range;
    }

    // Method to add a location to a task
    public void addLocationToTask(Location location) {
        locations.add(location);
    }

}
