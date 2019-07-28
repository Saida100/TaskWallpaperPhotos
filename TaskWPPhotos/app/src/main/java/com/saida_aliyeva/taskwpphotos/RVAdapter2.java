package com.saida_aliyeva.taskwpphotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RVAdapter2 extends RecyclerView.Adapter<RVViewHolder2>{

    List<Example> urlsList;
    Context context;
    Listener listener;

    public RVAdapter2(List<Example> urlsList, Context context, Listener listener) {
        this.urlsList = urlsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RVViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);
        return new RVViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder2 holder, int position) {
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
