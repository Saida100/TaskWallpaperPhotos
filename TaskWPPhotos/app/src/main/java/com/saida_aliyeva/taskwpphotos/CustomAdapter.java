package com.saida_aliyeva.taskwpphotos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    Context context;
    List<Example> exampleList;
    Listener listener;
    private static final int VIEW_TYPE_ITEM=5;
    private static final int VIEW_TYPE_LOADING=1;

    public CustomAdapter(Context context, List<Example> exampleList,Listener listener) {
        this.context = context;
        this.exampleList = exampleList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = null;
        if (viewType == VIEW_TYPE_ITEM) {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            return new DataViewHolder(root);
        } else {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_progress, parent, false);
            return new ProgressViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Log.e("exPos", String.valueOf(getItemViewType(position)));
        try{
            switch (getItemViewType(position)){
                case VIEW_TYPE_ITEM:
                    DataViewHolder dataViewHolder= (DataViewHolder) holder;
                    dataViewHolder.bind(exampleList.get(position),listener);
                    break;
                case VIEW_TYPE_LOADING:
                    break;


            }
        }catch(Exception e){
            e.printStackTrace();
        }

//        if (holder instanceof DataViewHolder) {
//       //     ((DataViewHolder) holder).imageView.setImageResource(Integer.parseInt(exampleList.get(position).getThumb()));
//
//            Picasso.get().load(exampleList.get(position).getThumb()).into(((DataViewHolder) holder).imageView);
//              //   ((DataViewHolder) holder).textView.setText(exampleList.get(position).getThumb());
//
//
//        } else {
//            //Do whatever you want. Or nothing !!
//        }
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (exampleList.get(position) != null)
            return VIEW_TYPE_ITEM;
        else
            return VIEW_TYPE_LOADING;
    }

    public void addNullData() {
        exampleList.add(null);
        notifyItemInserted(exampleList.size() - 1);
    }

    public void removeNull() {
        exampleList.remove(exampleList.size() - 1);
        notifyItemRemoved(exampleList.size());
    }

    public void addData(List<Example> exampleList) {
        exampleList.addAll(exampleList);
        notifyDataSetChanged();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class DataViewHolder extends CustomViewHolder {
        ImageView imageView;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumb);
        }
        public void bind(final Example example, final Listener listener){
            String photoUrl=example.getUrls().getThumb();
            Picasso.get().load(photoUrl).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPhotoClick(example);
                }
            });

        }

    }

    public class ProgressViewHolder extends CustomViewHolder {
        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

// https://ayusch.com/android-paginated-recyclerview-with-progress-bar/
}
