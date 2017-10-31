package com.example.ana.subjectmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab_PdfsFragment extends Fragment {

    String subjectName;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> listaDePdf;

    public Tab_PdfsFragment ( String subjectName ) {
        this.subjectName = subjectName;
    }

    public Tab_PdfsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_pdfs, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        listaDePdf = new ArrayList<String>();
        listaDePdf.add("Oi");
        listaDePdf.add("Oi1");
        listaDePdf.add("Oi2");
        listaDePdf.add("Oi3");
        listaDePdf.add("Oi4");
        listaDePdf.add("Oi5");
        listaDePdf.add("Oi6");

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(listaDePdf);
        recyclerView.setAdapter(recyclerViewAdapter);

        //TextView teste = (TextView)view.findViewById(R.id.textPdf);
        //teste.setText(subjectName);
        return view;
    }

}
