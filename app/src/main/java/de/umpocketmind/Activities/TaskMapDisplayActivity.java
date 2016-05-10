package de.umpocketmind.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.R;

public class TaskMapDisplayActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Location> receivedLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_map_display);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent receivedIntent = getIntent();
        Log.i("§§§§§§§", receivedIntent.toString());
        if (receivedIntent != null && receivedIntent.hasExtra("allLocations")){
            receivedLocations = (ArrayList<Location>)receivedIntent.getSerializableExtra("allLocations");

            for (Location l: receivedLocations) {
                Log.i("§§§§§§§§", l.toString());
                LatLng latLng = new LatLng(l.getLatitude(), l.getLongtitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(l.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }

    }
}
