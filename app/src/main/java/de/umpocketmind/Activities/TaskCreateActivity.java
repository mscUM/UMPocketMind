//author: Janos
package de.umpocketmind.Activities;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;
import de.umpocketmind.FunctionalityClasses.Location;
import de.umpocketmind.FunctionalityClasses.Task;
import de.umpocketmind.FunctionalityClasses.TaskManager;
import de.umpocketmind.R;
public class TaskCreateActivity extends ActionBarActivity {
    private TaskManager taskManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_task_create);
        taskManager = new TaskManager(this);
        activateAddButton();
    }
    private void showAllTasks()
    {
        List<Task> taskList = taskManager.getAllTasks();
        ArrayAdapter<Task> taskArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_item,
                                taskList
                        );
        ListView taskListView = (ListView) findViewById(R.id.Listview_TaskList);
        taskListView.setAdapter(taskArrayAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        taskManager.open();
        showAllTasks();
    }
    @Override
    protected void onPause() {
        super.onPause();
        taskManager.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflating the menu in order to add items to the Action Bar
        getMenuInflater().inflate(R.menu.menu_task_create, menu);
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
    private void activateAddButton() {
        Button buttonAddProduct = (Button) findViewById(R.id.btnAddTask);
        final EditText editTextName = (EditText) findViewById(R.id.taskCreate_EditTextName);
        final EditText editTextDescription = (EditText) findViewById(R.id.taskCreateEditTextDesc);
        final EditText editTextRange = (EditText) findViewById(R.id.taskCreate_EditTextRange);
        //final EditText editTextLocation = (EditText) findViewById(R.id.taskCreateEditTextLocation);
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editTextName.getText().toString();
                String stringDescription = editTextDescription.getText().toString();
                String stringRange = editTextRange.getText().toString();
               // String stringLocation = editTextLocation.getText().toString();
                /*if(TextUtils.isEmpty(name)) {
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
                }*/
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

                /*int location = Integer.parseInt(stringLocation);
                editTextName.setText("");
                editTextDescription.setText("");
                editTextRange.setText("");
                editTextLocation.setText("");*/

                taskManager.createTask(name, description, range);
                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                showAllTasks();
            }
        });
    }
}
