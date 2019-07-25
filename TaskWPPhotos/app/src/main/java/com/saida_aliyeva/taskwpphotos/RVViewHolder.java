package com.saida_aliyeva.taskwpphotos;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class RVViewHolder extends RecyclerView.ViewHolder {
    ImageView raw;
    ImageView full;
    ImageView regular;
    ImageView small;
    ImageView thumb;


    public RVViewHolder(@NonNull View itemView) {
        super(itemView);
        raw = itemView.findViewById(R.id.raw);
        full = itemView.findViewById(R.id.full);
        regular = itemView.findViewById(R.id.regular);
        small = itemView.findViewById(R.id.small);
        thumb = itemView.findViewById(R.id.thumb);
    }

    public void bind(final Urls urls, final Listener listener) {


        Picasso.get().load(urls.getRaw()).into(raw);
        Picasso.get().load(urls.getFull()).into(full);
        Picasso.get().load(urls.getRegular()).into(regular);
        Picasso.get().load(urls.getSmall()).into(small);
        Picasso.get().load(urls.getThumb()).into(thumb);
        Log.e("log_url", urls.getRaw());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPhotoClick(urls);


            }
        });

    }
}
