package com.app.final03.Model;

import android.location.Location;

import static android.location.LocationManager.*;

public class Place  {
    private int id;
    private String name;
    private  String address;
    private  double latitude;
    private  double longitude;

    public Place(int id, String name, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location getLocation(){
        Location location=  new Location(GPS_PROVIDER);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return  location;
    }

    public  int  distanceTo(Location location){
        return (int) getLocation().distanceTo(location);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }



}
