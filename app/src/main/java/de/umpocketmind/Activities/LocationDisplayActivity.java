// author by Chengqi

package de.umpocketmind.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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
    }

    private void showLocationDetail()
    {
        //create intent
        Intent locationDetailIntent = getIntent();
        //receive the data from LocationListDisplayActivity
        Location locationDetail = (Location) locationDetailIntent.getSerializableExtra("LocationDetail");
        String locationName = locationDetail.getName();
        String locationDescription = locationDetail.getDescription();
        //put data into layout
        TextView locationNameView = (TextView) findViewById(R.id.locationName);
        TextView locationDescriptionView = (TextView) findViewById(R.id.locationDescription);
        locationNameView.setText(locationName);
        locationDescriptionView.setText(locationDescription);
    }

}

