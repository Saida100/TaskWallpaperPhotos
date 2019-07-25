package com.saida_aliyeva.taskwpphotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVViewHolder>{

    List<Urls> urlsList;
    Context context;
    Listener listener;

    public RVAdapter(List<Urls> urlsList, Context context, Listener listener) {
        this.urlsList = urlsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        holder.bind(urlsList.get(position),listener);


    }

    @Override
    public int getItemCount() {
        return urlsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
