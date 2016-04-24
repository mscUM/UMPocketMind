package de.umpocketmind.FunctionalityClasses;

import java.util.ArrayList;

import de.umpocketmind.FunctionalityClasses.Location;

/**
 * Created by eva on 24.04.16.
 */
public class Task {

    private int id;
    private String name;
    private String description;
    private double range;
    private ArrayList<Location> locations;

    public Task(int id, String name, String description, double range) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.range = range;
        this.locations = new ArrayList<>();
    }

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

    public void addLocationToTask(Location location) {
        locations.add(location);
    }

}
