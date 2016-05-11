//author: chengqi

package de.umpocketmind.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.LocationManager;
import de.umpocketmind.R;




public class LocationListDisplayActivity extends AppCompatActivity {

    //call database
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list_display);
        locationManager = new LocationManager(this);
        //locationManager.open();
        //showAllLocations();
        //locationManager.close();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        locationManager.open();
        showAllLocations();
        locationManager.close();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    //show all location names in a list
    private void showAllLocations(){
        //get all location
        final List<Location> locationList = locationManager.getAllLocations();
        //create a array list to store location names
        List<String> locationNames = new ArrayList<>();
        //get all names of locations
        for (Location location:locationList) {
            locationNames.add(location.getName());
        }
        //push the list into Adapter
        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<>
                (
                        this,
                        android.R.layout.select_dialog_item,
                        locationNames
                );
        //list view layout
        ListView locationListView = (ListView) findViewById(R.id.locationList);
        locationListView.setAdapter(locationArrayAdapter);
        // create a listener of click event
        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
            //create intent
            Intent locationDetailIntent = new Intent();
            locationDetailIntent.setClass(LocationListDisplayActivity.this, LocationDisplayActivity.class);
            //transfer data to the second activity
            locationDetailIntent.putExtra("LocationDetail",locationList.get(position) );
            //start the second activity
            LocationListDisplayActivity.this.startActivity(locationDetailIntent);
          }
        });
    }//showallLocations

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflating the menu
        getMenuInflater().inflate(R.menu.menu_for_locationlistdisplay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Specify what happens in case an action bar item is clicked
        int id = item.getItemId();

        // Check which option was selected
        if (id == R.id.action_location_create) {
            // create intent and start LocationCreateActivity with explicit intent
            Intent locationcreateIntent = new Intent(this, LocationCreateActivityTest.class);
            boolean taskInfo = false;
            locationcreateIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(locationcreateIntent);
            return true;
        }

        if (id == R.id.action_show_tasks) {
            // create intent and start TaskListDisplayActivity with explicit intent
            Intent showTasksIntent = new Intent(this, TaskListDisplayActivity.class);
            boolean taskInfo = false;
            showTasksIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(showTasksIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}//main
