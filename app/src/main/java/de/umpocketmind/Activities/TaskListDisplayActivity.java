//author: Janos
package de.umpocketmind.Activities;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
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
    protected void onResume()
    {
        super.onResume();
        taskManager.open();
        showAllTasks();
        taskManager.close();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        taskManager.close();
    }
    private void  showAllTasks()
    {
        //taskManager.insertMockDataIntoDatabase();
        List<Task> taskList = taskManager.getAllTasks();
        List<String> taskNames = new ArrayList<>();
        for (Task task:taskList
                ) {
            taskNames.add(task.getName());
        }
        //locationManager.deleteAllTasksFromDatabase();
        ArrayAdapter<String> taskArrayAdapter =
                new ArrayAdapter<>
                        (
                                this,
                                android.R.layout.select_dialog_item,
                                taskNames
                        );
        ListView taskListView = (ListView) findViewById(R.id.Listview_TaskList);
        taskListView.setAdapter(taskArrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflating the menu in order to add items to the Action Bar
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
        return super.onOptionsItemSelected(item);
    }
}
