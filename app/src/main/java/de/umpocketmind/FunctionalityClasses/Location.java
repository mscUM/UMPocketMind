package de.umpocketmind.FunctionalityClasses;

/**
 * Created by eva on 24.04.16.
 */
public class Location {

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
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public double getLongtitude() {
        return longtitude;
    }
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
