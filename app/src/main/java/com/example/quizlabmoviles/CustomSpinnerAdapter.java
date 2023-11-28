package com.example.quizlabmoviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<SpinnerItem> {
    private final Context context;

    public CustomSpinnerAdapter(Context context, List<SpinnerItem> items) {
        super(context, 0, items);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageType);
        TextView textView = convertView.findViewById(R.id.textType);


        SpinnerItem item = getItem(position);

        if (item != null) {
            if (item.getImageResourceId() != -1) {
                imageView.setImageResource(item.getImageResourceId());
            } else {
                imageView.setImageResource(R.drawable.vacio);
            }
            textView.setText(item.getText());
        }

        return convertView;
    }
}

