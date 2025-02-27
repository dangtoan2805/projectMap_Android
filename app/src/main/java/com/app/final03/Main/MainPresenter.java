package com.app.final03.Main;

import android.content.Context;

import com.app.final03.Map.Map;
import com.app.final03.Model.Place;
import com.app.final03.Model.myModal;

import java.util.ArrayList;

public class MainPresenter implements myModal.myModelListener {
    //Attr static
    public static final Integer[] Range=new Integer[]{200,500,750,1000,2000,5000};
    public static  ArrayList<Integer> listIDSave= new ArrayList<>();
    public static ArrayList<String> listHistory= new ArrayList<>();
    //Attr of Main Presenter
    private myModal model;
    private  int distance;
    private  MainPresenterListener listener;

    //Constructor
    public MainPresenter(MainPresenterListener listener) {
        model= new myModal(this);
        this.listener=listener;
    }
        //Distance
    public  int getDistance(){return  distance;}

    public  void  changeDistance(int dist){
        distance=Range[dist];
    }
        //Data
    public  void saveData(){
        model.saveData();
    }

    public  void  readData(Context context){
        model.readData(context);
    }
        //List Save

    public ArrayList<Place> getListPlaceWithDistance(){
        return  model.getListWithDistance(distance, Map.locationCurr);
    }

    public  ArrayList<Place>  getDataListPlaceSave(){
        return model.getListPlaceSave(listIDSave);
    }

    @Override
    public void onSuccess(ArrayList<Integer> lisIDSave, ArrayList<String> lisHistory) {
        listIDSave= lisIDSave;
        listHistory=lisHistory;
    }

    @Override
    public void onFail() {
        listener.onDataFail();
    }

    public interface MainPresenterListener{
        void onDataFail();
    }
}
