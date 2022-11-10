package com.example.smartpatroladmin.Models;

import java.sql.Timestamp;
import java.util.Date;

public class Patrols {

    Timestamp startingTime;
    String startingLocation;
    String endingLocation;

    public Timestamp getStartingTime() {
        return startingTime;
    }


    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getEndingLocation() {
        return endingLocation;
    }

    public void setEndingLocation(String endingLocation) {
        this.endingLocation = endingLocation;
    }

    public void setStartingTime(com.google.firebase.Timestamp timestamp) {this.startingTime=startingTime;
    }
}
