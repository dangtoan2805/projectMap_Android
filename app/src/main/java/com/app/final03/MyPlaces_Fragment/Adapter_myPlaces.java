package com.app.final03.MyPlaces_Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.final03.Model.Place;
import com.app.final03.R;

import java.util.ArrayList;

public class Adapter_myPlaces extends BaseAdapter {
    private ArrayList<Place> placeArrayList;

    public Adapter_myPlaces(ArrayList<Place> placeArrayList) {
        this.placeArrayList = placeArrayList;
    }

    @Override
    public int getCount() {
        return placeArrayList.size();
    }

    @Override
    public Place getItem(int position) {
        return placeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return placeArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view==null)
            view=View.inflate(parent.getContext(), R.layout.item_listview_myplaces,null);
        //Binding
        Place place= placeArrayList.get(position);
        ((TextView) view.findViewById(R.id.txtName)).setText(place.getName());
        ((TextView) view.findViewById(R.id.txtAddress)).setText(place.getAddress());
        return view;
    }
}
