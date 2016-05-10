//author: Janos and Martin
package de.umpocketmind.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.FunctionalityClasses.TaskManager;
import de.umpocketmind.R;

public class TaskListDisplayActivity extends ActionBarActivity {
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
    }


    private void showAllTasks()
    {
        List<Task> taskList = taskManager.getAllTasks();
        Log.i("!!!!!!!!!!!!!!!!!!!!!!!", "--");
        ArrayAdapter<Task> taskArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_item,
                                taskList
                        );
        ListView taskListView = (ListView) findViewById(R.id.Listview_TaskList);
        taskListView.setAdapter(taskArrayAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("Test", adapterView.getItemAtPosition(position).toString());
                //String taskInfo = (String) adapterView.getItemAtPosition(position).toString();
                Task currentTask = (Task) adapterView.getItemAtPosition(position);
                Log.i("LLDA", "onItemClick called");
                // create intent, start activity with explicit intent
                Intent taskDisplayIntent = new Intent();
                taskDisplayIntent.setClass(TaskListDisplayActivity.this, TaskDisplayActivity.class);
                taskDisplayIntent.putExtra("currentTask", currentTask);
                TaskListDisplayActivity.this.startActivity(taskDisplayIntent);


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

        // Check which option was selected
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
