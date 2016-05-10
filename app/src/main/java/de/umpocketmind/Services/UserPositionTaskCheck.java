package de.umpocketmind.Services;

import android.Manifest;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import java.util.ArrayList;
import de.umpocketmind.Activities.TaskListDisplayActivity;
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
        if (!serviceIsCheckingPositions) {
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
                Log.v("BACKEND", "LÄUFT");
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
            sendNotification();
        }

        // Stop connection of GoogleAPIClient
        mGoogleApiClient.disconnect();
    }

    private void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //.setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("POCKETMIND")
                        .setContentText("You're within the range of a task you might want to complete!");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, TaskListDisplayActivity.class);

        // Artificial back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(TaskListDisplayActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        int mId = 0;
        mNotificationManager.notify(mId, mBuilder.build());
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