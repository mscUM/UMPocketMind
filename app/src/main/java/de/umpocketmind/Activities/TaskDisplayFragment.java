//author: Janos

package de.umpocketmind.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.umpocketmind.R;

public class TaskDisplayFragment extends Fragment {

    public TaskDisplayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_display, container, false);

        return rootView;
    }
}