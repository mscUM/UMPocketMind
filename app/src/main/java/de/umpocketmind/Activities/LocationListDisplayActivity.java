//author: chengqi

package de.umpocketmind.Activities;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import de.umpocketmind.FunctionalityClasses.DatabaseConnectorLocations;
import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.LocationManager;
import de.umpocketmind.R;

public class LocationListDisplayActivity extends AppCompatActivity {

   private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list_display);
        locationManager = new LocationManager(this);
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
        locationManager.close();
    }

    private void  showAllLocations()
    {
        //Log.i("lldActivity", "Method showAllLocation called");
        locationManager.insertMockDataIntoDatabase();
        List<Location> locationList = locationManager.getAllLocations();
        List<String> locationNames = new ArrayList<>();
        for (Location location:locationList
             ) {
            locationNames.add(location.getName());
        }
        locationManager.deleteMockDataFromDatabase();
        ArrayAdapter<String> locationArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_item,
                                locationNames
                        );
        ListView locationListView = (ListView) findViewById(R.id.locationList);
        locationListView.setAdapter(locationArrayAdapter);

    }

}//main
