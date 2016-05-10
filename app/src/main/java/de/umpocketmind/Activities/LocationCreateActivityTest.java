package de.umpocketmind.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.LocationManager;
import de.umpocketmind.R;

/**
 * Created by Martin on 10.05.16.
 */
public class LocationCreateActivityTest extends AppCompatActivity {

    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_create_test);
        locationManager = new LocationManager(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();

    }



    public void addLocationToDB(View v)
    {

        //Toast.makeText(this, "Add to task geclickt", Toast.LENGTH_SHORT).show();
        EditText locationNameEditText = (EditText) findViewById(R.id.locationCreate_EditTextName);
        EditText locationDescEditText = (EditText) findViewById(R.id.locationCreate_EditTextDesc);
        EditText locationLongitudeEditText = (EditText) findViewById(R.id.locationCreate_EditTextLongitude);
        EditText locationLatitudeEditText = (EditText) findViewById(R.id.locationCreate_EditTextLatitude);


        String locationName = locationNameEditText.getText().toString();
        String locationDesc = locationDescEditText.getText().toString();
        Double locationLongitude = new Double(locationLongitudeEditText.getText().toString());
        Double locationLatitude =new Double(locationLatitudeEditText.getText().toString());

        //Toast.makeText(this, locationName + " " + locationDesc + ", " + locationLongitude + ", " + locationLatitude, Toast.LENGTH_LONG).show();

        Location newLocation = new Location(0, locationName, locationDesc, locationLongitude, locationLatitude);

        locationManager.open();
        locationManager.createLocation(newLocation);
        locationManager.close();

    }

}


