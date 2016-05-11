package de.umpocketmind.FunctionalityClasses;

import java.io.Serializable;
import java.util.ArrayList;
import android.util.Log;

/**
 * Created by eva on 24.04.16.
 */
public class Task implements Serializable {

    private long id;
    private String name;
    private String description;
    private double range;
    private ArrayList<Location> locations;

    public Task(long id, String name, String description, double range) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.range = range;
        this.locations = new ArrayList<>();
        Log.i("Task", "Task-Object created:" + this.id + this.name + this.description + this.range + this.locations);
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
        Log.i("Task", "Task-Object-Id updated:" + this.id);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        Log.i("Task", "Task-Object-Name updated:" + this.name);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        Log.i("Task", "Task-Object-Description updated:" + this.description);
    }
    public double getRange() {
        return range;
    }
    public void setRange(double range) {
        this.range = range;
        Log.i("Task", "Task-Object-Range updated:" + this.range);
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
    public void addLocationToTask(Location location) {
        locations.add(location);
        Log.i("Task", "Task-Object-Location added:" + location);
    }

    @Override
    public String toString()
    {
        return this.name + ", " + this.description + ", " + this.range + ", " + this.locations;
    }
}
