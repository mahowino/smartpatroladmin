package com.example.smartpatroladmin.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Map;

public class Schedule implements Parcelable {
    Map<String, Date> Monday,Tuesday,Wednesday,Thursday,Friday;

    protected Schedule(Parcel in) {
    }

    public Schedule() {
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    public Map<String, Date> getMonday() {
        return Monday;
    }

    public void setMonday(Map<String, Date> monday) {
        Monday = monday;
    }

    public Map<String, Date> getTuesday() {
        return Tuesday;
    }

    public void setTuesday(Map<String, Date> tuesday) {
        Tuesday = tuesday;
    }

    public Map<String, Date> getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Map<String, Date> wednesday) {
        Wednesday = wednesday;
    }

    public Map<String, Date> getThursday() {
        return Thursday;
    }

    public void setThursday(Map<String, Date> thursday) {
        Thursday = thursday;
    }

    public Map<String, Date> getFriday() {
        return Friday;
    }

    public void setFriday(Map<String, Date> friday) {
        Friday = friday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
