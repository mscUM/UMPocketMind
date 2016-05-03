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
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        locationManager.close();
    }

    private void  showAllLocations()
    {
        locationManager.insertMockDataIntoDatabase();
        List<Location> locationList = locationManager.getAllLocations();
        locationManager.deleteMockDataFromDatabase();
        ArrayAdapter<Location> locationArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_item,
                                locationList
                        );
        ListView locationListView = (ListView) findViewById(R.id.locationList);
        locationListView.setAdapter(locationArrayAdapter);

    }

}//main
