package com.example.carson_ho.toptabbar;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by newlife on 2018/1/28.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public FruitAdapter(@NonNull Context context, @LayoutRes int textViewResourceId, @NonNull List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView)view.findViewById(R.id.fruit_name);
        ImageView fruitArrow = (ImageView)view.findViewById(R.id.fruit_arrow);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        fruitArrow.setImageResource(fruit.getImageArrowId());
        return view;
    }
}
