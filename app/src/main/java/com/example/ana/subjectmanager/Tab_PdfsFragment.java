package com.example.ana.subjectmanager;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab_PdfsFragment extends Fragment {

    String subjectName;
    String nomeDoArquivo;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> listaDePdf;

    private static final int PDF_CODE = 1;

    public Tab_PdfsFragment ( String subjectName ) {
        this.subjectName = subjectName;
        this.nomeDoArquivo = subjectName + "Pdf.txt";
    }

    public Tab_PdfsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_pdfs, container, false);

        Button btn = (Button) view.findViewById(R.id.inserirPdf);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        listaDePdf = new ArrayList<String>();
        listaDePdf = readFile(listaDePdf);
        //listaDePdf.add("Oi");
        //listaDePdf.add("Oi1");


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(listaDePdf);
        recyclerView.setAdapter(recyclerViewAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null ) {
                    startActivityForResult(intent, PDF_CODE);
                }

                //listaDePdf.add("Teste");
                //recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        //TextView teste = (TextView)view.findViewById(R.id.textPdf);
        //teste.setText(subjectName);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String uriString = uri.toString();
            try {
                //arquivosList.add(new Arquivo(uri.toString()));
                listaDePdf.add(uriString);
                addNoFile(uriString);
                recyclerViewAdapter.notifyDataSetChanged();
            } catch (Exception e ) {
                e.printStackTrace();
            }

        }
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
