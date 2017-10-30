package com.example.ana.subjectmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab_VideosFragment extends Fragment {

    String subjectName;

    public Tab_VideosFragment ( String subjectName ) {
        this.subjectName = subjectName;
    }

    public Tab_VideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab_videos, container, false);
        TextView teste = (TextView)view.findViewById(R.id.textVideo);
        teste.setText(subjectName);
        return view;
    }

}
