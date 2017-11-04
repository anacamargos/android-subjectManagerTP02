package com.example.ana.subjectmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab_LinksFragment extends Fragment {

    String subjectName;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> listaDeLinks;
    //ArrayAdapter<String> adapter;

    public Tab_LinksFragment (String subjectName) {
        this.subjectName = subjectName;
    }

    public Tab_LinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab_links, container, false);

        Button btn = (Button) view.findViewById(R.id.inserirLink);

        listaDeLinks = new ArrayList<String>();



        //ListView listView = (ListView) view.findViewById(R.id.listLink);
        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaDeLinks);
        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaDeLinks);
        //listView.setAdapter(adapter);

        listaDeLinks.add("Oi");
        listaDeLinks.add("Oi1");
        listaDeLinks.add("Oi2");
        listaDeLinks.add("Oi3");
        listaDeLinks.add("Oi4");
        listaDeLinks.add("Oi5");
        listaDeLinks.add("Oi6");

        //TextView teste = (TextView)view.findViewById(R.id.textLink);
        //teste.setText(subjectName);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);




        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(listaDeLinks);
        recyclerView.setAdapter(recyclerViewAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaDeLinks.add("TESTEEEE");
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        return view;


    }



}
