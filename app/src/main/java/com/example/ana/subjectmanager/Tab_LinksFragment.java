package com.example.ana.subjectmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                final View view1 = inflater.inflate(R.layout.dialog_link, null);
                mBuilder.setView(view1);

                final EditText linkEt = (EditText) view1.findViewById(R.id.eTLink);
                Button inserirLinkBtn = (Button) view1.findViewById(R.id.btnInserirLink);

                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                inserirLinkBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!linkEt.getText().toString().isEmpty() ) {
                            String video = linkEt.getText().toString();
                            listaDeLinks.add(video);
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                            recyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //listaDeLinks.add("TESTEEEE");
                //recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        return view;


    }



}
