package com.example.ana.subjectmanager;


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
import static android.app.Activity.RESULT_OK;

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

    private static final int PDF_CODE = 1;

    public Tab_PdfsFragment ( String subjectName ) {
        this.subjectName = subjectName;
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
        listaDePdf.add("Oi");
        listaDePdf.add("Oi1");


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
                recyclerViewAdapter.notifyDataSetChanged();
            } catch (Exception e ) {
                e.printStackTrace();
            }

        }
    }
}
