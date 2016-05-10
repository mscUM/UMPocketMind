//author: Martin and Janos

package de.umpocketmind.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.R;


public class TaskDisplayActivity extends AppCompatActivity{

    private Task currentTask;
    private ArrayList<Location> taskLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_display);
        showTaskDetail();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void showTasksOnMap(View v){


        //Toast.makeText(this, taskLocations.toString(), Toast.LENGTH_SHORT).show();


        Intent taskShowLocIntent = new Intent();
        taskShowLocIntent.setClass(TaskDisplayActivity.this, TaskMapDisplayActivity.class);
        taskShowLocIntent.putExtra("allLocations", (ArrayList<Location>) taskLocations);
        TaskDisplayActivity.this.startActivity(taskShowLocIntent);

    }


    private void showTaskDetail()
    {

        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("currentTask"))
        {

            currentTask = (Task) receivedIntent.getSerializableExtra("currentTask");

            /*final List<Location> taskLocationsList = currentTask.getLocations();
            Log.i("§§", currentTask.toString());

            ArrayAdapter<Location> taskLocationArrayAdapter =
                    new ArrayAdapter<>
                            (
                                    this,
                                    android.R.layout.select_dialog_multichoice,
                                    taskLocationsList
                            );
            ListView taskLocationsListView = (ListView) findViewById(R.id.taskDetail_Locations);
            taskLocationsListView.setAdapter(taskLocationArrayAdapter);
            */
            Location dummyLocation = new Location(0, "LearningCenter", "LC UNI", 8.461785999999961, 49.4829556);
            taskLocations.add(dummyLocation);
            currentTask.addLocationToTask(dummyLocation);
            TextView taskNameTextView = (TextView) findViewById(R.id.taskDetail_name);
            TextView taskDescTextView = (TextView) findViewById(R.id.taskDetail_description);
            TextView taskRangeTextView = (TextView) findViewById(R.id.taskDetail_range);
            TextView taskLocationTextView = (TextView) findViewById(R.id.taskDetail_Locations);
            taskNameTextView.setText(currentTask.getName().toString());
            taskDescTextView.setText(currentTask.getDescription().toString());
            taskRangeTextView.setText(currentTask.getRange() + "");
            //final List<Location>
            taskLocationTextView.setText((currentTask.getLocations())+ "");
        }
    }

}

