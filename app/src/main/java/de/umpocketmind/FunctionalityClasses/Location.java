package de.umpocketmind.FunctionalityClasses;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by eva on 24.04.16.
 */
public class Location implements Serializable {

    private long id;
    private String name;
    private String description;
    private double longtitude;
    private double latitude;

    public Location(long id, String name, String description, double longtitude, double latitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.longtitude = longtitude;
        this.latitude = latitude;
        Log.i("Location", "Location-Object created:" + this.id + this.name + this.description + this.longtitude + this.latitude);
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
        Log.i("Location", "Location-Object-Id updated:" + this.id);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        Log.i("Location", "Location-Object-Name updated:" + this.name);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        Log.i("Location", "Location-Object-Description updated:" + this.description);
    }
    public double getLongtitude() {
        return longtitude;
    }
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
        Log.i("Location", "Location-Object-Longtitude updated:" + this.longtitude);
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
        Log.i("Location", "Location-Object-Latitude updated:" + this.latitude);
    }

    @Override
    public String toString()
    {
        return this.name + ", " + this.description + ", " + this.longtitude + ", " + this.latitude;

    }

}
