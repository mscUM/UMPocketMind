// author by Chengqi

package de.umpocketmind.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import de.umpocketmind.FunctionalityClasses.LocationManager;
import de.umpocketmind.R;

public class LocationDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_display);

        LocationManager mLocationManager = new LocationManager(this);

    }




}
