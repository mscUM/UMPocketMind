//author: Janos and Martin
package de.umpocketmind.Activities;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;
import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.LocationManager;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.FunctionalityClasses.TaskManager;
import de.umpocketmind.R;
import android.widget.AbsListView;


public class TaskCreateActivity extends AppCompatActivity{

    private TaskManager taskManager;
    private LocationManager locationManager;
    ArrayAdapter<String> adapter;
    private ArrayList<Location> taskLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        locationManager = new LocationManager(this);
        taskManager = new TaskManager(this);
        getAllLocations();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    private void getAllLocations(){
        locationManager.open();

        final List<Location> taskLocationList = locationManager.getAllLocations();
        ArrayAdapter<Location> taskArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_multichoice,
                                taskLocationList
                        );
        final ListView taskLocationListView = (ListView) findViewById(R.id.taskCreate_ListViewTaskLocations);
        taskLocationListView.setAdapter(taskArrayAdapter);
        locationManager.close();
        //taskLocationListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        taskLocationListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Log.i("ich bin hier", "ich bin hier");
        taskLocationListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });



    }//getAllLocations


    public void addTaskToList(View v)
    {

        Toast.makeText(this, "Add to task geclickt", Toast.LENGTH_SHORT).show();
        EditText taskNameEditText = (EditText) findViewById(R.id.taskCreate_EditTextName);
        EditText taskDescEditText = (EditText) findViewById(R.id.taskCreate_EditTextDesc);
        EditText taskRangeEditText = (EditText) findViewById(R.id.taskCreate_EditTextRange);

        String taskName = taskNameEditText.getText().toString();
        String taskDesc = taskDescEditText.getText().toString();
        double taskRange = Double.valueOf(taskRangeEditText.getText().toString());

        Task addTask = new Task(0, taskName, taskDesc, taskRange);
        //Location locationToAdd = new Location(0, );
        //addTask.addLocationToTask(locationToAdd);

        taskManager.open();
        taskManager.createTask(addTask);
        taskManager.close();

        Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        taskNameEditText.setText("");
        taskDescEditText.setText("");
        taskRangeEditText.setText("");


    }

}//main
