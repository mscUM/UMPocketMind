//author: Janos

package de.umpocketmind.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.R;


public class TaskDisplayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_display);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflating the menu in order to add items to the Action Bar
        getMenuInflater().inflate(R.menu.menu_taskdetail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Specify what happens in case an action bar item is clicked
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showtaskDetail()
    {
        Intent taskDisplayIntent = getIntent();
        Task taskDisplay = (Task) taskDisplayIntent.getSerializableExtra("TaskDisplay");
        String taskName = taskDisplay.getName();
        String taskDescription = taskDisplay.getDescription();
        double taskRange = taskDisplay.getRange();
        ArrayList<Location> taskLocation = taskDisplay.getLocations();

        TextView taskNameView = (TextView) findViewById(R.id.TaskName);
        TextView taskDescriptionView = (TextView) findViewById(R.id.TaskDescription);
        //TextView taskRangeView = (TextView) findViewById(R.id.TaskRange);
        //TextView taskLocationView = (TextView) findViewById(R.id.TaskLocation);
        taskNameView.setText(taskName);
        taskDescriptionView.setText(taskDescription);
        // need double type!        taskRangeView.setCameraDistance(taskRange);
        // need array type!         taskLocationView.setText(taskLocation);
    }

}