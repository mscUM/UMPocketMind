//author: Janos

package de.umpocketmind.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.R;


public class TaskDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_display);
        showtaskDetail();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //showtaskDetail();
    }

    private void showtaskDetail()
    {
        Intent receivedIntent = TaskDisplayActivity.this.getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra(Intent.EXTRA_TEXT))
        {
            Toast.makeText(this, "IstDa", Toast.LENGTH_SHORT).show();
        }
    }

}