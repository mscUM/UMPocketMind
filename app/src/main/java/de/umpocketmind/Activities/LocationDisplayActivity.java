// author by Chengqi

package de.umpocketmind.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.LocationManager;
import de.umpocketmind.R;

public class LocationDisplayActivity extends AppCompatActivity {

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_display);
        locationManager = new LocationManager(this);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        locationManager.open();
        showLocationDetail();
        locationManager.close();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        locationManager.close();
    }

    private void showLocationDetail()
    {
        Intent locationDetailIntent = getIntent();
        Location locationDetail = (Location) locationDetailIntent.getSerializableExtra("LocationDetail");
        String locationName = locationDetail.getName();
        String locationDescription = locationDetail.getDescription();
        TextView locationNameView = (TextView) findViewById(R.id.locationName);
        TextView locationDescriptionView = (TextView) findViewById(R.id.locationDescription);
        locationNameView.setText(locationName);
        locationDescriptionView.setText(locationDescription);
    }

}

