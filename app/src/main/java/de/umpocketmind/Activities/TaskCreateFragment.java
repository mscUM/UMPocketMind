//author: Janos
package de.umpocketmind.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.umpocketmind.R;


public class TaskCreateFragment extends Fragment {

    public TaskCreateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_create, container, false);

        return rootView;
    }
}
