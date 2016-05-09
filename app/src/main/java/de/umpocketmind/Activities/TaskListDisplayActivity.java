//author: Janos
package de.umpocketmind.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.FunctionalityClasses.TaskManager;
import de.umpocketmind.R;

public class TaskListDisplayActivity extends AppCompatActivity {
    private TaskManager taskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list_display);
        taskManager = new TaskManager(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        taskManager.open();
        showAllTasks();
        taskManager.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        taskManager.close();
    }











    private void showAllTasks() {
        //locationManager.insertMockDataIntoDatabase();
        final List<Task> taskList = taskManager.getAllTasks();
        List<String> taskNames = new ArrayList<>();
        for (Task task : taskList
                ) {
            taskNames.add(task.getName());
        }
        //locationManager.deleteAllLocationsFromDatabase();
        ArrayAdapter<String> taskArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_item,
                                taskNames
                        );


        ListView taskListView = (ListView) findViewById(R.id.Listview_TaskList);
        taskListView.setAdapter(taskArrayAdapter);


        //View rootView = null;  // = inflater.inflate(R.layout.activity_task_list_display, container, false);

        //ListView tasklistListView = (ListView) rootView.findViewById(R.id
        //      .Listview_TaskList);

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String taskInfo = (String) adapterView.getItemAtPosition(position);
                Log.i("LLDA", "onItemClick called");
                // create intent, start activity with explicit intent


                // Andy's Intent for location
                //Intent locationDetailIntent = new Intent();
                //locationDetailIntent.setClass(LocationListDisplayActivity.this, LocationDisplayActivity.class);
                //locationDetailIntent.putExtra("LocationDetail",locationList.get(position) );
                //LocationListDisplayActivity.this.startActivity(locationDetailIntent);

                Intent taskDisplayIntent = new Intent();
                taskDisplayIntent.setClass(TaskListDisplayActivity.this, TaskDisplayActivity.class);
                taskDisplayIntent.putExtra("TaskDisplay", taskList.get(position) );
                TaskListDisplayActivity.this.startActivity(taskDisplayIntent);

                //old intent
                /*Intent taskdetailIntent = new Intent(TaskListDisplayActivity.this, TaskDisplayActivity.class);
                taskdetailIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
                startActivity(taskdetailIntent);*/

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflating the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Specify what happens in case an action bar item is clicked
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        // Check whether option was selected
        if (id == R.id.action_task_create) {
            // create intent and start TaskCreateActivity with explicit intent
            Intent taskcreateIntent = new Intent(this, TaskCreateActivity.class);
            boolean taskInfo = false;
            taskcreateIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(taskcreateIntent);

            return true;
        }

        if (id == R.id.action_show_locations) {

            Intent showLocationsIntent = new Intent(this, LocationListDisplayActivity.class);
            boolean taskInfo = false;
            showLocationsIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
            startActivity(showLocationsIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
