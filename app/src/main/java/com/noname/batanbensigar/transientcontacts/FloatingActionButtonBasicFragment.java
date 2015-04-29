package com.noname.batanbensigar.transientcontacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Batan Bensigar on 4/29/2015.
 */
public class FloatingActionButtonBasicFragment extends Fragment implements FloatingActionButton.OnCheckedChangeListener {

    private final static String TAG = "FloatingActionButtonBasicFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fab_layout, container, false);

        // Make this {@link Fragment} listen for changes in both FABs.
        FloatingActionButton fab1 = (FloatingActionButton) rootView.findViewById(R.id.fab_close);
        fab1.setOnCheckedChangeListener(this);

        return rootView;
    }


    @Override
    public void onCheckedChanged(FloatingActionButton fabView, boolean isChecked) {
        // When a FAB is toggled, log the action.
        switch (fabView.getId()){
            case R.id.fab_close:
                Toast.makeText(fabView.getContext(),"Action button clicked",Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
    }
}
