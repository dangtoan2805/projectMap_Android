package com.app.final03.Map;

import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.final03.Model.Place;
import com.app.final03.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Map extends Fragment implements OnMapReadyCallback ,
        GoogleMap.OnMarkerClickListener,
        MapHelper.MapHelperListener {

    public static Location locationCurr= new Location(LocationManager.GPS_PROVIDER);
    private  boolean flagStart=false;
    private GoogleMap map;
    private  MapHelper helper;
    private ArrayList<Marker> markerArrayList;
    private  onMapListener listener;

    public Map(onMapListener listener){
        this.listener=listener;
    }

    @Override
    public void onResume() {
        super.onResume();
      if (flagStart){
            showLocationCurr();
            flagStart=false;
      }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment supportMapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("TAG","MAP READY");
        map=googleMap;
        map.setOnMarkerClickListener(this);
        helper= new MapHelper(getContext(),getActivity(),map,this);
        flagStart=true;
        onResume();
    }

    public void showLocationCurr(){
        if (map!=null){
            helper.checkPermisionAndLocation();
            helper.requestLocationCurr();
            helper.changeTitlePin("Your here");
        }
    }

    public void changeLocation(Location location, String title){
        locationCurr=location;
        helper.changeTitlePin(title);
        helper.displayLocation(location);
    }

    public void onChangeMarker(int pos){
        Marker marker= markerArrayList.get(pos);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom((marker.getPosition()),15));
        marker.showInfoWindow();
    }

    public void showNearby(int distance,ArrayList<Place> placeArrayList ) {
           markerArrayList=  helper.showNearby(distance,locationCurr,placeArrayList,map);
    }

    @Override
    public void onGetLocationSuccess(Location location) {
        Log.e("Tag" ,"SUCCESS : "+ location);
        locationCurr=location;
        helper.displayLocation(location);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getTag()!=null)
            listener.onMakerClicked((Integer) marker.getTag());
        return false;
    }

    public interface onMapListener {
        void  onMakerClicked(int pos);
    }
}
