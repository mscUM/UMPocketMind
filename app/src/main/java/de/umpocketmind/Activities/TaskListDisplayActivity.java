//author: Janos and Martin
package de.umpocketmind.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.umpocketmind.FunctionalityClasses.GoogleAPIConnector;
import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.LocationManager;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.FunctionalityClasses.TaskLocationsManager;
import de.umpocketmind.FunctionalityClasses.TaskManager;
import de.umpocketmind.R;
import de.umpocketmind.Services.UserPositionTaskCheck;

public class TaskListDisplayActivity extends ActionBarActivity {
    private TaskManager taskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list_display);
        taskManager = new TaskManager(this);

        //todo: das hier wieder wegnehmen

        Location locationLondon = new Location(0, "London", "Hi! London here!", -0.070035, 51.5073262);
        Location locationMannheim = new Location(0, "Mannheim", "Das ist Mannheim", 8.3619924, 49.4999557);
        Location locationParis = new Location(0, "Paris", "This is Paris", 2.2945071, 48.8582606);
        LocationManager locationManager = new LocationManager(this);
        locationManager.open();
        Location mockLocationLondon = locationManager.createLocation(locationLondon);
        Location mockLocationMannheim = locationManager.createLocation(locationMannheim);
        Location mockLocationParis = locationManager.createLocation(locationParis);

        ArrayList<Location> testAllLocation = locationManager.getAllLocations();
        Log.i("$$", testAllLocation.toString());
        Location testLocation = locationManager.getLocationById(1);
        Log.i("§§§§§§§§§§1", testLocation.toString());

        locationManager.close();

        Task taskHoliday = new Task(0, "Holiday", "Holidayyyyy", 5000);
        taskHoliday.addLocationToTask(mockLocationLondon);
        taskHoliday.addLocationToTask(mockLocationParis);
        Log.i("§§§§§§§§§§2", taskHoliday.toString());
        Task taskStudy = new Task(0, "Study", "This is to study", 1000);
        taskStudy.addLocationToTask(mockLocationMannheim);
        TaskManager taskManager = new TaskManager(this);
        taskManager.open();
        taskManager.createTask(taskHoliday);

        Log.i("taskHoliday", taskHoliday.toString());
        taskManager.createTask(taskStudy);
        Log.i("taskStudy", taskStudy.toString());
        taskManager.close();




        //todo: endtodo
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check if user permitted to retrieve their location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            String[] myPermissions = {"ACCESS_FINE_LOCATION"};
            ActivityCompat.requestPermissions(this, myPermissions, 1);

        }else{
            Intent i = new Intent(this, UserPositionTaskCheck.class);
            startService(i);

        }
        taskManager.open();
        showAllTasks();
        taskManager.close();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults){

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

            Intent i = new Intent(this, UserPositionTaskCheck.class);
            startService(i);
        }else{
            Toast.makeText(this,
                    "Please grant the requested permissions. If you deny, you can't use the app. We are sorry", Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(15000);} catch (InterruptedException e) { }
            android.os.Process.killProcess(android.os.Process.myPid());

        }

    }

    private void showAllTasks()
    {
        /*
        GoogleAPIConnector googleAPIConnector = new GoogleAPIConnector(this);
        android.location.Location currentUserPosition;
        while(true){
            currentUserPosition = googleAPIConnector.getCurrentUserPosition();
            if(currentUserPosition != null){
                Log.i("LOCATION", "nicht mehr null");
                break;
            }
            Log.i("LOCATION", "still null");
            try { Thread.sleep(5000); } catch (InterruptedException e) { }
        }
        //android.location.Location currentUserPosition = googleAPIConnector.getCurrentUserPosition();
        double currentLongitude = currentUserPosition.getLongitude();
        double currentLatitude = currentUserPosition.getLatitude();
        */
        ArrayList<Task> taskList = taskManager.getAllTasks();
        Log.i("GETALLTASKS", taskManager.getAllTasks().toString());
        //taskList = taskManager.sortTasksByDistance(taskList, currentLongitude, currentLatitude);
        ArrayAdapter<Task> taskArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_item,
                                taskList
                        );
        ListView taskListView = (ListView) findViewById(R.id.Listview_TaskList);
        taskListView.setAdapter(taskArrayAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("Test", adapterView.getItemAtPosition(position).toString());
                Task currentTask = (Task) adapterView.getItemAtPosition(position);
                Log.i("LLDA", "onItemClick called");
                Intent taskDisplayIntent = new Intent();
                taskDisplayIntent.setClass(TaskListDisplayActivity.this, TaskDisplayActivity.class);
                taskDisplayIntent.putExtra("currentTask", currentTask);
                TaskListDisplayActivity.this.startActivity(taskDisplayIntent);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflating the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Specify what happens in case an action bar item is clicked
        int id = item.getItemId();

        // Check which option was selected
        if (id == R.id.action_task_create) {
            // create intent and start TaskCreateActivity with explicit intent
            Intent taskcreateIntent = new Intent(this, TaskCreateActivity.class);
            boolean taskInfo = false;
            taskcreateIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(taskcreateIntent);
            return true;
        }

        if (id == R.id.action_show_locations) {

            Intent showLocationsIntent = new Intent(this, LocationListDisplayActivity.class);
            boolean taskInfo = false;
            showLocationsIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(showLocationsIntent);
            return true;
        }

        if (id == R.id.action_add_location) {

            Intent addLocationIntent = new Intent(this, LocationCreateActivityTest.class);
            boolean taskInfo = false;
            addLocationIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(addLocationIntent);
            return true;
        }

        /*
        if (id == R.id.action_add_Mapslocation) {

            Intent addLocationIntent = new Intent(this, LocationCreateActivity.class);
            boolean taskInfo = false;
            addLocationIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(addLocationIntent);
            return true;
        }
           */
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
