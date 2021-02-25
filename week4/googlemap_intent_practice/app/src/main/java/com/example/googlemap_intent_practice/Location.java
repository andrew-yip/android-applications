package com.example.googlemap_intent_practice;

public class Location {

    // DATA MEMBERS
    public double longitude;
    public double latitude;

    public Location (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location () {
        longitude = 0;
        latitude = 0;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLocation () {
        return "geo:" + latitude + "," + longitude + "," + "10";
    }
}
