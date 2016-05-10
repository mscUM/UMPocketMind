//author: Martin and Janos

package de.umpocketmind.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.R;


public class TaskDisplayActivity extends AppCompatActivity{

    private Task currentTask;

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
        String allLocation = "";
        allLocation = "" + currentTask;

        Toast.makeText(this, allLocation, Toast.LENGTH_SHORT).show();
        /*
        array vom typ Location weitergeben
        Intent taskShowLocIntent = new Intent();
        taskShowLocIntent.setClass(TaskDisplayActivity.this, TaskMapDisplayActivity.class);
        taskShowLocIntent.putExtra("showLocations", TaskLocations);
        TaskDisplayActivity.this.startActivity(taskShowLocIntent);
        */
    }


    private void showTaskDetail()
    {

        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("currentTask"))
        {
            //Toast.makeText(this, receivedIntent.getExtras().toString(), Toast.LENGTH_SHORT).show();
            currentTask = (Task) receivedIntent.getSerializableExtra("currentTask");
            //Log.i("getExtras", receivedIntent.getExtras().toString());

            TextView taskNameTextView = (TextView) findViewById(R.id.taskDetail_name);
            TextView taskDescTextView = (TextView) findViewById(R.id.taskDetail_description);
            TextView taskRangeTextView = (TextView) findViewById(R.id.taskDetail_range);
            taskNameTextView.setText(currentTask.getName().toString());
            taskDescTextView.setText(currentTask.getDescription().toString());
            taskRangeTextView.setText(currentTask.getRange() + "");
        }
    }

}

