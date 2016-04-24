package de.umpocketmind.FunctionalityClasses;

/**
 * Created by eva on 24.04.16.
 */
public class LocationManager {

    private static LocationManager locationManager;

    public static LocationManager getLocationManager() {
        if (locationManager == null) {
            locationManager = new LocationManager();
        }
        return locationManager;
    }

    public void createLocation() {
        // Todo
    }

    public void updateLocationById(int id) {
        // Todo
    }

    public void deleteLocationById(int id) {
        // Todo
    }

    public void getLocationById(int id) {
        // Todo
    }

    public void getAllLocations() {
        // Todo
    }

}
