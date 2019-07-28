package com.saida_aliyeva.taskwpphotos;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class RVViewHolder2 extends RecyclerView.ViewHolder {

    ImageView thumb;


    public RVViewHolder2(@NonNull View itemView) {
        super(itemView);

        thumb = itemView.findViewById(R.id.thumb);
    }

    public void bind(final Example example, final Listener listener) {


        Picasso.get().load(example.getUrls().getThumb()).into(thumb);
        Log.e("log_url", example.getUrls().getRaw());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPhotoClick(example);


            }
        });

    }
}
