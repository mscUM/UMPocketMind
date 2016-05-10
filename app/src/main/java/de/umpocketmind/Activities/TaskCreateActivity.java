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
                Log.i("ICH BIN HIER", "ICH BIN HIER"); // will be displayed
            }
        });

        /*Log.i("ICH bin HIER", "ICH bin HIER"); // won't be displayed --> stops in between
        SparseBooleanArray checked = taskLocationListView.getCheckedItemPositions();

        for (int i = 0; i < checked.size(); i++) {
            if(checked.valueAt(i) == true) {
                Tag tag = (Tag) taskLocationListView.getItemAtPosition(checked.keyAt(i));
                Log.i("xxxx", i + " " + tag);
            }
        }
*/
        /*
        SparseBooleanArray checked = taskLocationListView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            Log.i("ICH BIN HIER!", i + "");
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }
        /*
        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            //outputStrArr[i] = selectedItems.get(i);
            Log.i("outputStrArr[i]", selectedItems.get(i));
        }
        */

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



        Toast.makeText(this, "taskname: " + taskName + " Desc: " + taskDesc + " Range: " + taskRange, Toast.LENGTH_SHORT).show();

        taskManager.open();

        Task AddedTask = new Task(0, taskName, taskDesc, taskRange);

        Location dummyLocation = new Location(0, "LearningCenter", "LeCe UNI", 8.461785999999961, 49.4829556);
        taskLocations.add(dummyLocation);

        // locations einfügen

        /*public Task(long id, String name, String description, double range) {
        this.id = id;
        this.name = taskName;
        this.description = taskDesc;
        this.range = taskRange;
        this.locations = new ArrayList<>();
        Log.i("Task", "Task-Object created:" + this.id + this.name + this.description + this.range + this.locations);


    }*/



       /* public addTaskToList(String taskName, String taskDesc, double taskRange, Location dummyLocation)
        {
            this.taskName = taskName;
            this.taskDesc = taskDesc;
            this.taskRange = taskRange;
            this.dummyLocation = dummyLocation;
        }

    }*/

        /*Task addedTask = new Task() {
                String taskName;
                String taskDesc;
                Double taskRange;*/


        taskManager.close();


    }
/*
                String stringName = editTextName.getText().toString();
                String stringDescription = editTextDescription.getText().toString();
                String stringRange = editTextRange.getText().toString();
               // String stringLocation = editTextLocation.getText().toString();
                /(TextUtils.isEmpty(name)) {
                    editTextName.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(description)) {
                    editTextDescription.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(range)) {
                    editTextRange.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(location)) {
                    editTextLocation.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                int name = Integer.parseInt(stringName);
                editTextName.setText("");
                editTextDescription.setText("");
                editTextRange.setText("");
                //editTextLocation.setText("");

                int description = Integer.parseInt(stringDescription);
                editTextName.setText("");
                editTextDescription.setText("");
                editTextRange.setText("");
                //editTextLocation.setText("");

                int range = Integer.parseInt(stringRange);
                editTextName.setText("");
                editTextDescription.setText("");
                editTextRange.setText("");
                //editTextLocation.setText("");

                int location = Integer.parseInt(stringLocation);
                editTextName.setText("");
                editTextDescription.setText("");
                editTextRange.setText("");
                editTextLocation.setText("");

                taskManager.createTask(name, description, range);
                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

            }
        });
    } */
}
