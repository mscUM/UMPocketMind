//author: Janos

package de.umpocketmind.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.umpocketmind.R;


public class TaskListDisplayFragment extends Fragment {

    View v;



    public TaskListDisplayFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // declare options menu
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tasklistfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check whether option was selected
        int id = item.getItemId();
        if (id == R.id.action_task_create) {


            Toast.makeText(getActivity(), "Aktualisieren gedrückt!", Toast.LENGTH_LONG).show();


                return true;

        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String LOG_TAG = TaskListDisplayFragment.class.getSimpleName();

        Log.v(LOG_TAG, "verbose     - Meldung");
        Log.d(LOG_TAG, "debug       - Meldung");
        Log.i(LOG_TAG, "information - Meldung");
        Log.w(LOG_TAG, "warning     - Meldung");
        Log.e(LOG_TAG, "error       - Meldung");

        String [] taskListArray = {
                "Task 1",
                "Task 2",
                "Task 3",
                "Task 4",
                "Task 5",
                "Task 6",
                "Task 7",
                "Task 8",
                "Task 9"

                };



        List <String> taskList = new ArrayList<>(Arrays.asList(taskListArray));

        ArrayAdapter <String> taskListAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item_tasklist,
                        R.id.list_item_tasklist_textview,
                        taskList);

        View rootView = inflater.inflate(R.layout.fragment_tasklist, container, false);

        ListView tasklistListView = (ListView) rootView.findViewById(R.id
                .Listview_TaskList);
        tasklistListView.setAdapter(taskListAdapter);
        tasklistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String taskInfo = (String) adapterView.getItemAtPosition(position);

                // create intent, start activity with explicit intent
                Intent taskdetailIntent = new Intent(getActivity(), TaskDisplayActivity.class);
                taskdetailIntent.putExtra(Intent.EXTRA_TEXT, taskInfo);
                startActivity(taskdetailIntent);

            }
        });

        return rootView;



    }



}