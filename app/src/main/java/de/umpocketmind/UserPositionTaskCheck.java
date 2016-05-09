package de.umpocketmind;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.FunctionalityClasses.TaskManager;

/**
 * Created by eva on 09.05.16.
 */
public class UserPositionTaskCheck extends IntentService
        implements ConnectionCallbacks, OnConnectionFailedListener {

    private boolean serviceIsCheckingPositions;

    public UserPositionTaskCheck() {
        super("WorkThreadName");
        serviceIsCheckingPositions = false;
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (serviceIsCheckingPositions == false) {
            serviceIsCheckingPositions = true;
            checkUserPositionTask();
        }
    }

    private void checkUserPositionTask() {

        double longtitude = 0.0;
        double latitude = 0.0;
        TaskManager taskManager = new TaskManager(this);
        ArrayList<Task> taskList;
        boolean userShouldBeNotified = false;

        // Create an instance of GoogleAPIClient
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // Start connection of GoogleAPIClient
        mGoogleApiClient.connect();

        while(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Get current position of the user
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                longtitude = mLastLocation.getLongitude();
                latitude = mLastLocation.getLatitude();
            }

            // Find out if a task is within its range
            taskManager.open();
            taskList = taskManager.getAllTasks();
            for (Task task: taskList ) {
                if(taskManager.isUserPositionInTaskRange(task, longtitude, latitude)) {
                    userShouldBeNotified = true;
                    break;
                }
            }
            taskManager.close();

            // End loop to send a notification
            if(userShouldBeNotified) {
                break;
            }

            // Wait some time
            try { Thread.sleep(30000); } catch (InterruptedException e) { }
        }

        if (userShouldBeNotified) {
            //todo: notify user
        }

        // Stop connection of GoogleAPIClient
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}