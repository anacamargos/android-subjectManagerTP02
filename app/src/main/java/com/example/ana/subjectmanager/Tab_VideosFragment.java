package com.example.ana.subjectmanager;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab_VideosFragment extends Fragment {

    String subjectName;
    String nomeDoArquivo;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> listaDeVideo;

    public Tab_VideosFragment ( String subjectName ) {
        this.subjectName = subjectName;
        this.nomeDoArquivo = subjectName + "Video.txt";
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
        listaDeVideo = readFile(listaDeVideo);
        //listaDeVideo.add("Oi");
        //listaDeVideo.add("Oi1");


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
                            addNoFile(video);
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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                // do whatever
                String video_path = listaDeVideo.get(position);
                if ( video_path.contains("youtube")) {
                    Uri uri = Uri.parse(video_path);
                    uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else if ( video_path.contains("youtu.be")) {
                    String [] aux = video_path.split("youtu.be/");
                    String aux2 = aux[1];
                    String video_path2 = "http://youtube.com/watch?v=" + aux2;
                    Uri uri = Uri.parse(video_path2);
                    uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }

            @Override public void onLongItemClick(View view, int position) {
                // do whatever
            }

        }));

        //TextView teste = (TextView)view.findViewById(R.id.nomeVideo);
        //teste.setText(subjectName);
        return view;
    }

    public ArrayList<String> readFile (ArrayList<String> listaDeVideo) {
        File file = getContext().getFileStreamPath(nomeDoArquivo);
        String lineFromFile;
        if(file.exists()) {
            try {
                //System.out.println("PASSEI POR AQUIIIIIII");
                //BufferedReader reader = new BufferedReader(new FileReader(nomeDoArquivo));
                BufferedReader reader = new BufferedReader(new InputStreamReader(getContext().openFileInput(nomeDoArquivo)));
                while ((lineFromFile = reader.readLine()) != null) {
                    //StringTokenizer tokens = new StringTokenizer(lineFromFile);
                    listaDeVideo.add(lineFromFile);
                    System.out.println(lineFromFile);
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }

        }
        return listaDeVideo;
    }

    public void addNoFile ( String adicional ) {
        try {
            FileOutputStream fos = getContext().openFileOutput(nomeDoArquivo, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(adicional + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (Exception e ) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Erro ao salvar arquivo", Toast.LENGTH_SHORT).show();
        }
    }
}
