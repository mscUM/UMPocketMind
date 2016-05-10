//author: Martin and Janos

package de.umpocketmind.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
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
    }

    private void showtaskDetail()
    {

        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("currentTask"))
        {
            //Toast.makeText(this, receivedIntent.getExtras().toString(), Toast.LENGTH_SHORT).show();
            Task currentTask = (Task) receivedIntent.getSerializableExtra("currentTask");
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

