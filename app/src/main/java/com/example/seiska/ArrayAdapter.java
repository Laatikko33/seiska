package com.example.seiska;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapter extends android.widget.ArrayAdapter<MyEntity> {
    private Context context;
    ArrayList<MyEntity> dataset;


    public ArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.dataset = (ArrayList<MyEntity>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layoutti,parent,false);
        LinearLayout linearLayout = (LinearLayout) view;
        TextView textView1 = view.findViewById(R.id.textView1);
        TextView textView2 = view.findViewById(R.id.textView2);
        textView1.setText(dataset.get(position).tapahtuma);
        textView2.setText(dataset.get(position).aika);
        return view;
    }
}
