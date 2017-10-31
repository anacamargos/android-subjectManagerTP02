package com.example.ana.subjectmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ana on 30/10/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<String> listaDeVideos;
    View view;
    ViewHolder viewHolder;

    /*public RecyclerViewAdapter(Class<Tab_VideosFragment> context, ArrayList<String> listaDeVideos) {
        this.context = context;
        this.listaDeVideos = listaDeVideos;
    }*/

    public RecyclerViewAdapter ( ArrayList<String> listaDeVideos) {
        this.listaDeVideos = listaDeVideos;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {

        String var =  listaDeVideos.get(position);
        holder.nomeVideoTV.setText(var);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return listaDeVideos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeVideoTV;
        //TextView teste;
        //ImageButton botaoWhats;

        public ViewHolder(View itemView) {
            super(itemView);

            nomeVideoTV = (TextView) itemView.findViewById(R.id.nomeVideo);
            //botaoWhats = (ImageButton) itemView.findViewById(R.id.image_button);
            //teste = (TextView) itemView.findViewById(R.id.teste);
        }

    }
}
