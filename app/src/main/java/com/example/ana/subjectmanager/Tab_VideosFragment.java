package com.example.ana.subjectmanager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab_VideosFragment extends Fragment {

    String subjectName;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> listaDeVideo;

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

        Button btn = (Button) view.findViewById(R.id.inserirVideo);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        listaDeVideo = new ArrayList<String>();
        listaDeVideo.add("Oi");
        listaDeVideo.add("Oi1");


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(listaDeVideo);
        recyclerView.setAdapter(recyclerViewAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                final View view1 = inflater.inflate(R.layout.dialog_video, null);
                mBuilder.setView(view1);

                final EditText videoEt = (EditText) view1.findViewById(R.id.eTVideo);
                Button inserirVideoBtn = (Button) view1.findViewById(R.id.btnInserirVideo);

                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                inserirVideoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!videoEt.getText().toString().isEmpty() ) {
                            String video = videoEt.getText().toString();
                            listaDeVideo.add(video);
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                            recyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //listaDeVideo.add("TESTEEEE");
                //recyclerViewAdapter.notifyDataSetChanged();
            }
        });


        //TextView teste = (TextView)view.findViewById(R.id.nomeVideo);
        //teste.setText(subjectName);
        return view;
    }

}
