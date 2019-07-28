package com.saida_aliyeva.taskwpphotos;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentInfo extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView width=view.findViewById(R.id.width);
        TextView height=view.findViewById(R.id.height);
        TextView color=view.findViewById(R.id.color);
        TextView createdAt=view.findViewById(R.id.createdAt);
        TextView updatedAt=view.findViewById(R.id.updatedAt);
        TextView alt_description=view.findViewById(R.id.alt_description);
        width.setText("width: "+getArguments().getString("width"));
        height.setText("height: "+getArguments().getString("height"));
        color.setText("color:  "+getArguments().getString("color"));
        createdAt.setText("createdAt:  "+getArguments().getString("created_at"));
        updatedAt.setText("updated_at:  "+getArguments().getString("updated_at"));
        alt_description.setText("alt_description:  "+getArguments().getString("alt_description"));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
